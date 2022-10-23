package com.huahua.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.user.dtos.UserLoginDto;
import com.huahuo.model.user.dtos.UserSignDto;
import com.huahuo.model.user.pojos.User;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2022-10-23 10:48:36
*/
public interface UserService extends IService<User> {
    public ResponseResult login(UserLoginDto dto);

    public ResponseResult sign(UserSignDto dto);
}
