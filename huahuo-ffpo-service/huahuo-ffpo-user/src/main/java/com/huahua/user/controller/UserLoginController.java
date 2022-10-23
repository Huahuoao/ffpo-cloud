package com.huahua.user.controller;

import com.huahua.user.service.UserService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.user.dtos.UserLoginDto;
import com.huahuo.model.user.dtos.UserSignDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2022/10/23 15:38
 */
@RestController
@RequestMapping("/user/login")
public class UserLoginController {
@Autowired
    UserService userService;
    @PostMapping("/login_auth")

    public ResponseResult login(@RequestBody UserLoginDto dto){
        return userService.login(dto);
    }
    @PostMapping("/sign_up")

    public ResponseResult sign(@RequestBody UserSignDto dto){
        return userService.sign(dto);
    }


}
