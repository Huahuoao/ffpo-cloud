package com.huahuo.stamp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.stamp.dtos.StampPageDto;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.model.stamp.pojos.UserStampDetailDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Administrator
 * @description 针对表【stamp_detail】的数据库操作Service
 * @createDate 2022-10-25 20:44:24
 */
public interface StampDetailService extends IService<StampDetail> {
    ResponseResult list(StampPageDto dto);

    ResponseResult create(UserStampDetailDto dto);

    ResponseResult update(UserStampDetailDto dto);
}
