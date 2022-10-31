package com.huahuo.feign;

import com.huahuo.model.user.pojos.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

/**
 * @作者 花火
 * @创建日期 2022/10/28 10:48
 */
@FeignClient(name = "huahuo-ffpo-user", path = "/user/feign")
@Component
public interface UserFeignService {
    @GetMapping("/gps/{id}")
    ArrayList<String> getGPS(@PathVariable("id") Integer id);

    @PostMapping("/get/id/random/")
    Integer getRandomUserId();

    @GetMapping("/get/{id}")
    User getById(@PathVariable("id") Integer id);

    @PostMapping("/save")
    void save(@RequestBody User user);
}
