package com.huahuo.shop.controller;

import com.huahuo.feign.UserFeignService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.shop.dtos.ShoppingDto;
import com.huahuo.shop.service.ShopService;
import com.huahuo.utils.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @作者 花火
 * @创建日期 2022/11/7 17:01
 */
@RestController
@RequestMapping("/user")
public class UserShoppingController {
    @Autowired
    private ShopService service;
    @Autowired
    private UserFeignService userFeignService;

    /**
     * 购买
     *
     * @param dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    @PostMapping("/buy")
    public ResponseResult shopping(@RequestBody ShoppingDto dto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        RedisUtils.clearStampCaChe(userFeignService.getUserIdFromThread());
        return service.shopping(dto);
    }
}
