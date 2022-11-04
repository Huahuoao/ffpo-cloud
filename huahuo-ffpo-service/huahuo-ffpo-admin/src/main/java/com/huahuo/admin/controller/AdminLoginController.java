package com.huahuo.admin.controller;

import com.huahuo.admin.service.AdminService;
import com.huahuo.model.admin.dtos.AdminLoginDto;
import com.huahuo.model.admin.dtos.AdminSignDto;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.user.dtos.UserLoginDto;
import com.huahuo.model.user.dtos.UserSignDto;
import com.netflix.client.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2022/11/4 9:01
 */
@RequestMapping("/login")
@RestController
public class AdminLoginController {
    @Autowired
    AdminService adminService;
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody AdminLoginDto dto){



        return adminService.login(dto);
    }

    @PostMapping("/sign_up")

    public ResponseResult sign(@RequestBody AdminSignDto dto) throws ClientException {
        return adminService.sign(dto);
    }
}
