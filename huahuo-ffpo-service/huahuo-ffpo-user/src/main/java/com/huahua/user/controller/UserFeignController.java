package com.huahua.user.controller;

import com.huahua.user.mapper.UserMapper;
import com.huahua.user.service.UserService;
import com.huahuo.model.user.pojos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @作者 花火
 * @创建日期 2022/10/28 10:43
 */
@RequestMapping("/user/feign")
@RestController
@Slf4j
public class UserFeignController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;

    @GetMapping("/gps/{id}")
    public ArrayList<String> getGPS(@PathVariable("id") Integer id) {
        User byId = service.getById(id);
        log.info(byId.toString());
        log.info(byId.getLongitude());
        log.info(byId.getLatitude());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(byId.getLongitude());
        arrayList.add(byId.getLatitude());

        return arrayList;
    }

    @PostMapping("/get/id/random/")
    public Integer getRandomUserId() {
        return mapper.getRandonId().getId();
    }




    @PostMapping("/save")
    public void save(@RequestBody User user)
    {
        service.updateById(user);
    }

   @GetMapping("/get/{id}")
    public User getById (@PathVariable("id") Integer id){
        return service.getById(id);
   }
}
