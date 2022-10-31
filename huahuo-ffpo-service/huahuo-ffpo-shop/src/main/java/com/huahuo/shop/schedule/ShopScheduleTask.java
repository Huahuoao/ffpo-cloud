package com.huahuo.shop.schedule;

import com.huahuo.model.shop.pojos.Shop;
import com.huahuo.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 花火
 * @创建日期 2022/10/31 16:10
 */
@Component
@Slf4j
public class ShopScheduleTask {
    @Autowired
    ShopService service;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 每天十二点自动更新商店
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void onSaleStampsSchedule()
    {
        service.onSaleSchedule();
        List<Shop> onsals = redisTemplate.opsForList().range("onsale", 0, -1);
        log.info(onsals.toString()+"上架成功");
    }
}
