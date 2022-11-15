package com.huahuo.model.mail.dtos;

import lombok.Data;

/**
 * @作者 花火
 * @创建日期 2022/11/15 9:23
 */
@Data
public class EsMailDto {
    private Integer id;
    private String sendTime;
    private String stampImg;
    private String sendUserId;
    private String title;
    private String tags;
    private String msg;
}
