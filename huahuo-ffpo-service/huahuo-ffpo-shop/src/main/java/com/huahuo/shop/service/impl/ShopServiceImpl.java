package com.huahuo.shop.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.feign.StampFeignService;
import com.huahuo.feign.UserFeignService;
import com.huahuo.model.common.dtos.PageRequestDto;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.shop.dtos.ShoppingDto;
import com.huahuo.model.shop.pojos.Shop;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.model.user.pojos.User;
import com.huahuo.shop.mapper.ShopMapper;
import com.huahuo.shop.service.ShopService;
import com.huahuo.utils.common.WeightedRandomUtil;
import io.swagger.models.auth.In;
import org.apache.commons.beanutils.PropertyUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

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
    private StampFeignService stampFeignService;
    @Autowired
    private UserFeignService userFeignService;
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
            stamp.setRealPrice((int) (discount * stamp.getPrice()));
        }
        updateBatchById(stamps);
        if (redisTemplate.opsForList().size("onsale") > 0)
            redisTemplate.opsForList().leftPop("onsale");
        //存入
        redisTemplate.opsForList().rightPushAll("onsale", stamps);
    }

    @Override
    public  ResponseResult listStampShops(PageRequestDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<Shop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shop::getIsOnsale,1);
        queryWrapper.orderByAsc(Shop::getRealPrice);
        IPage pageResult = page (page,queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }


    //购买邮票
    @Override
    public ResponseResult shopping(ShoppingDto dto) {
        Shop byId = getById(dto.getGoodId());
        //检测有没有钱
        Integer price = byId.getPrice();
        User user = userFeignService.getById(dto.getUserId());
        Integer money = user.getCoinNum();
        if(money-price<0)
        {
            return  ResponseResult.okResult(201,"金币不足，购买失败");
        }
        //检测集邮册满了吗
        if(user.getStampNum()>=user.getStampMaxNum())
        {
            return  ResponseResult.okResult(202,"集邮册已满，购买失败");
        }
        user.setCoinNum(money-price);
        //update
        userFeignService.save(user);
        //创建购买的邮票实体
        Stamp stamp = stampFeignService.getStamp(byId.getStampId());
        StampDetail  stampDetail = new StampDetail();
        BeanUtils.copyProperties(stamp,stampDetail);
        stampDetail.setIsLike(0);
        stampDetail.setGetTime(DateUtil.now());
        stampDetail.setOwnnerId(dto.getUserId());
        //创建购买邮票实体的磨损度
        Random random = new Random();
        double v = random.nextDouble();
        if(v<0.5) v+=0.35;
        String str = String.format("%.2f",v);
        double vv = Double.parseDouble(str);
        stampDetail.setLife(vv);
        stampFeignService.saveStamptoUser(stampDetail);
        return new ResponseResult(AppHttpCodeEnum.SUCCESS.getCode(), "购买成功");
    }
}




