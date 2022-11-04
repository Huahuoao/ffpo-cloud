package com.huahuo.stamp.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.stamp.dtos.StampPageDto;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.model.stamp.pojos.UserStampDetailDto;
import com.huahuo.stamp.mapper.StampDetailMapper;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.stamp.service.StampService;
import com.huahuo.utils.thread.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【stamp_detail】的数据库操作Service实现
 * @createDate 2022-10-26 10:18:26
 */
@Service
public class StampDetailServiceImpl extends ServiceImpl<StampDetailMapper, StampDetail>
        implements StampDetailService {


    @Autowired
    private StampService stampService;

    @Override
    public ResponseResult list(StampPageDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<StampDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StampDetail::getOwnnerId, ThreadLocalUtil.getUser().getId());
        switch (dto.getOrderWay()) {
            case 0:
                lambdaQueryWrapper.orderByDesc(StampDetail::getGetTime);
                break;
            case 1:
                lambdaQueryWrapper.orderByAsc(StampDetail::getLevel);
                break;
            case 3:
                lambdaQueryWrapper.orderByAsc(StampDetail::getLife);
                break;
        }
        IPage pageResult = page(page, lambdaQueryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult create(UserStampDetailDto dto) {
        StampDetail stampDetail = new StampDetail();
        stampDetail.setSignature(dto.getSignature());
        stampDetail.setStampTypeId(dto.getStampTypeId());
        stampDetail.setGetTime(DateUtil.now());

        stampDetail.setOwnnerId(ThreadLocalUtil.getUser().getId());
        Integer stampTypeId = stampDetail.getStampTypeId();
        Stamp byId = stampService.getById(stampTypeId);
        stampDetail.setLife(0.99);
        stampDetail.setMsg(byId.getMsg());
        stampDetail.setType(byId.getType());
        stampDetail.setImg(byId.getImg());
        save(stampDetail);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "获得新的邮票！");
    }

    @Override
    public ResponseResult update(UserStampDetailDto dto) {
        StampDetail byId = getById(dto.getId());
        byId.setSignature(dto.getSignature());
        updateById(byId);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "修改签名成功！");
    }
}




