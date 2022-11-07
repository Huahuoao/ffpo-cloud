package com.huahuo.feign;


import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @作者 花火
 * @创建日期 2022/10/27 23:11
 */   //admin
    @FeignClient(name="huahuo-ffpo-stamp",path = "/admin")
    @Component
public interface StampFeignService {
    @GetMapping("/feign/getstamp/simple/{id}")
    public Stamp getStamp(@PathVariable("id") Integer id);
    @GetMapping("/feign/getstampimg/{id}")
    public String getStampImgAndUpdateLife(@PathVariable("id") Integer id);
    @GetMapping("/feign/getstampimg/simple/{id}")
    public String getStampImg(@PathVariable("id") Integer id);

    @PostMapping("/feign/save")
    public void saveStamptoUser(@RequestBody StampDetail stampDetail);

}
