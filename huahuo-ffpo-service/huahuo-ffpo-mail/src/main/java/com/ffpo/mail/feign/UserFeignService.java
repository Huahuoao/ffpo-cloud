package com.ffpo.mail.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/10/28 10:48
 */
@FeignClient(name="huahuo-ffpo-user",path = "/user/feign")
@Transactional
public interface UserFeignService {
    @GetMapping("/gps/{id}")
    public Map<String,Double> getGPS(@PathVariable("id") Integer id);

    @PostMapping("/get/id/random/")
    public Integer getRandomUserId();
}
