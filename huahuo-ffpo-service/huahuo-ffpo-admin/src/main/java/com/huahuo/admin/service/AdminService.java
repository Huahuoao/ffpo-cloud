package com.huahuo.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.admin.dtos.AdminLoginDto;
import com.huahuo.model.admin.dtos.AdminSignDto;
import com.huahuo.model.admin.pojos.Admin;
import com.huahuo.model.common.dtos.ResponseResult;

/**
* @author Administrator
* @description 针对表【admin】的数据库操作Service
* @createDate 2022-11-03 11:37:04
*/
public interface AdminService extends IService<Admin> {


    ResponseResult login(AdminLoginDto dto);
    ResponseResult sign(AdminSignDto dto);
}
