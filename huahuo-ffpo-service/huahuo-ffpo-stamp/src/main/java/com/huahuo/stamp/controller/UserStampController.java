package com.huahuo.stamp.controller;

import cn.hutool.core.date.DateUtil;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.stamp.dtos.StampPageDto;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.model.stamp.pojos.UserStampDetailDto;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.stamp.service.StampService;
import com.huahuo.utils.thread.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * @作者 花火
 * @创建日期 2022/10/25 22:30
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserStampController {
    @Autowired
    private StampDetailService service;


    @PostMapping("/create")
    @CacheEvict(value = "StampBagCache",allEntries = true)
    public ResponseResult create(@RequestBody UserStampDetailDto dto){
        return service.create(dto);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody UserStampDetailDto dto){
       return  service.update(dto);
    }

    @PostMapping("/list")
    @Cacheable(value = "StampBagCache",key = "#dto.userId+'_'+#dto.orderWay+'_'+#dto.page+'_'+#dto.size")
    public ResponseResult list(@RequestBody StampPageDto dto) {
        return service.list(dto);
    }




}
