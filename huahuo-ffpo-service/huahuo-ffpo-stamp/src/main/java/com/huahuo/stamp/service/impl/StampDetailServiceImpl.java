package com.huahuo.stamp.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.stamp.mapper.StampDetailMapper;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.stamp.service.StampService;
import com.huahuo.utils.thread.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Administrator
 * @description 针对表【stamp_detail】的数据库操作Service实现
 * @createDate 2022-10-25 16:28:42
 */
@Service
@Transactional
@Slf4j
public class StampDetailServiceImpl extends ServiceImpl<StampDetailMapper, StampDetail>
        implements StampDetailService {

    @Autowired
    private StampService service;

    @Override
    public ResponseResult update(String signature) {
        Integer id = ThreadLocalUtil.getUser().getId();
        LambdaQueryWrapper<StampDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StampDetail::getOwnnerId, id);
        StampDetail one = getOne(queryWrapper);
        one.setSignature(signature);
        updateById(one);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "修改专属描述成功！");
    }

    @Override
    //线程获取bug 时间bug
    public ResponseResult create(StampDetail stampDetail) {
        log.info("进入方法");
        Stamp byId = service.getById(stampDetail.getStampTypeId());
        log.info("获得对象", byId.toString());
        stampDetail.setMsg(byId.getMsg());
        save(stampDetail);
        stampDetail.setOwnnerId(ThreadLocalUtil.getUser().getId());
        stampDetail.setGetTime(LocalDateTime.now());
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "获得新的邮票成功！");
    }
}




