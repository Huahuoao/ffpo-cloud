package com.huahuo.stamp.controller;


import com.huahuo.model.common.dtos.ResponseResult;

import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.stamp.service.StampDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @作者 花火
 * @创建日期 2022/10/25 16:27
 */
@RestController
@RequestMapping("/user")
public class UserStampController {

    @Autowired
    private StampDetailService service;


    @PostMapping("/create")
    public ResponseResult create(@RequestBody StampDetail stampDetail) {
        return service.create(stampDetail);
    }

    /**
     * 修改签名信息，给签名就好
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(String signature) {
        return service.update(signature);

    }


}
