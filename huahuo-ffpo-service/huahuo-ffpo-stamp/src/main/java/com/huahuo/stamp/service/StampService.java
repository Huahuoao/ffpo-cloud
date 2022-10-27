package com.huahuo.stamp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.UserStampDetailDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【stamp(邮票)】的数据库操作Service
 * @createDate 2022-10-25 10:47:20
 */
public interface StampService extends IService<Stamp> {
    String uploadImg(MultipartFile file);

    ResponseResult uploadStamp(Stamp stamp);


}
