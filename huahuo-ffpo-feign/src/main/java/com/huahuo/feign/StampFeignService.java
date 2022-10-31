package com.huahuo.feign;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * @作者 花火
 * @创建日期 2022/10/27 23:11
 */   //admin
    @FeignClient(name="huahuo-ffpo-stamp",path = "/admin")
    @Component
public interface StampFeignService {

    @GetMapping("/feign/getstampimg/{id}")
    public String getStampImgAndUpdateLife(@PathVariable("id") Integer id);
    @GetMapping("/feign/getstampimg/simple/{id}")
    public String getStampImg(@PathVariable("id") Integer id);
}
