package com.huahua.user.controller;

import com.huahua.user.mapper.UserMapper;
import com.huahua.user.service.UserService;
import com.huahuo.model.user.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 花火
 * @创建日期 2022/10/28 10:43
 */
@RequestMapping("/user/feign")
@RestController
public class UserFeignController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @GetMapping("/gps/{id}")
    public Map<String, Double> getGPS(@PathVariable("id") Integer id) {
        User byId = service.getById(id);
        Map map = new HashMap(3);
        map.put("longitude", byId.getLongitude());
        map.put("latitude", byId.getLatitude());
        return null;
    }

    @PostMapping("/get/id/random/")
    public Integer getRandomUserId() {
        return mapper.getRandonId().getId();
    }
}
