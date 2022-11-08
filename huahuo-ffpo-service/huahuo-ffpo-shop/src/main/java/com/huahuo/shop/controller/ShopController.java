package com.huahuo.shop.controller;

import com.huahuo.model.common.dtos.PageRequestDto;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.shop.service.FreeShopService;
import com.huahuo.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @作者 花火
 * @创建日期 2022/10/31 15:36
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private FreeShopService freeShopService;

    @PostMapping("/list/stamp")
    public ResponseResult listStampShops(@RequestBody PageRequestDto dto) {
        return shopService.listStampShops(dto);
    }

    @PostMapping("/list/free")
    public ResponseResult listFreeShops(@RequestBody PageRequestDto dto) {
        return freeShopService.listFreeShops(dto);
    }
}
