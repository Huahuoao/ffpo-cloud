package com.ffpo.mail.feign;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @作者 花火
 * @创建日期 2022/10/27 23:11
 */   //admin
    @FeignClient(name="huahuo-ffpo-stamp",path = "/admin")
    @Transactional
public interface StampFeignService {

    @GetMapping("/feign/getstampimg/{id}")
    public String getStampImg(@PathVariable("id") Integer id);
}
