package com.huahua.user.controller;

import com.huahua.user.service.UserService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.user.dtos.UserLoginDto;
import com.huahuo.model.user.dtos.UserSignDto;
import com.netflix.client.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2022/10/23 15:38
 */
@RestController
@RequestMapping("/login")
public class UserLoginController {
@Autowired
    UserService userService;


    @PostMapping("/login_auth")

    public ResponseResult login(@RequestBody UserLoginDto dto){
        return userService.login(dto);
    }
    @PostMapping("/sign_up")

    public ResponseResult sign(@RequestBody UserSignDto dto) throws ClientException {
        return userService.sign(dto);
    }
    @GetMapping("/sendsms/{phone}")
    public ResponseResult sendSMS(@PathVariable("phone") String phone) throws  Exception {
       if(userService.sendSMS(phone))
           return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(),"发送成功！");
       return ResponseResult.errorResult(AppHttpCodeEnum.SIGN_INVALID.getCode(),AppHttpCodeEnum.SIGN_INVALID.getErrorMessage());
    }

}
