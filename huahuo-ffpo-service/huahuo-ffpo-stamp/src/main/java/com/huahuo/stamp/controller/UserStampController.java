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

import java.time.LocalDateTime;

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
    @Autowired
    private StampService stampService;

    @PostMapping("/create")
    @CacheEvict(value = "StampBagCache",allEntries = true)
    public ResponseResult create(@RequestBody UserStampDetailDto dto){
        StampDetail stampDetail = new StampDetail();
        stampDetail.setSignature(dto.getSignature());
        stampDetail.setStampTypeId(dto.getStampTypeId());
        stampDetail.setGetTime(DateUtil.now());
        log.info(stampDetail.getGetTime());
        stampDetail.setOwnnerId(ThreadLocalUtil.getUser().getId());
        Integer stampTypeId = stampDetail.getStampTypeId();
        Stamp byId = stampService.getById(stampTypeId);
        stampDetail.setLife(0.99);
        stampDetail.setMsg(byId.getMsg());
        service.save(stampDetail);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(),"获得新的邮票！");
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody UserStampDetailDto dto){
        StampDetail byId = service.getById(dto.getId());
        byId.setSignature(dto.getSignature());
        service.updateById(byId);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(),"修改签名成功！");
    }

    @PostMapping("/list")
    @Cacheable(value = "StampBagCache",key = "#dto.userId+'_'+#dto.orderWay+'_'+#dto.page+'_'+#dto.size")
    public ResponseResult list(@RequestBody StampPageDto dto) {
        return service.list(dto);
    }
}
