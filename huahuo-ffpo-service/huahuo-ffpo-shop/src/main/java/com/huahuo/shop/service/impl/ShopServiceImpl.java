package com.huahuo.shop.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.shop.pojos.Shop;
import com.huahuo.shop.mapper.ShopMapper;
import com.huahuo.shop.service.ShopService;
import com.huahuo.utils.common.WeightedRandomUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【shop】的数据库操作Service实现
 * @createDate 2022-10-31 15:31:59
 */
@Service
@Transactional
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop>
        implements ShopService {
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onSaleSchedule() {
        List<Shop> stamps = shopMapper.onSale();
        Double discount;
        for (Shop stamp : stamps) {
            stamp.setIsOnsale(1);
            discount = WeightedRandomUtil.getRandom();
            stamp.setDiscount(discount);
            stamp.setRealPrice((int) (discount*stamp.getPrice()));
        }
        updateBatchById(stamps);
       if   (redisTemplate.opsForList().size("onsale") > 0)
             redisTemplate.opsForList().leftPop("onsale");
        //存入
           redisTemplate.opsForList().rightPushAll("onsale", stamps);
    }
}




