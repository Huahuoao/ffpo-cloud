package com.huahuo.stamp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.stamp.pojos.StampDetail;

/**
* @author Administrator
* @description 针对表【stamp_detail】的数据库操作Service
* @createDate 2022-10-25 16:28:42
*/
public interface StampDetailService extends IService<StampDetail> {


    public ResponseResult update(String signature);

    public ResponseResult create(StampDetail stampDetail);


}
