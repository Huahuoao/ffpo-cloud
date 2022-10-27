package com.huahuo.stamp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.stamp.dtos.StampPageDto;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.stamp.mapper.StampDetailMapper;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.utils.thread.ThreadLocalUtil;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【stamp_detail】的数据库操作Service实现
 * @createDate 2022-10-26 10:18:26
 */
@Service
public class StampDetailServiceImpl extends ServiceImpl<StampDetailMapper, StampDetail>
        implements StampDetailService {

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
}




