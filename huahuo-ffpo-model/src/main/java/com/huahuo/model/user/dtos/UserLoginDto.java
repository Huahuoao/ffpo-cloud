package com.huahuo.model.user.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * @作者 花火
 * @创建日期 2022/10/23 10:42
 */
@Data
public class UserLoginDto implements Serializable {
    private String username;
    private String password;
    private String salt;
    private String phone;
    //纬度
    private String latitude;
    //经度
    private String longitude;
    private String address;
}
