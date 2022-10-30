package com.huahuo.model.mail.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * @作者 花火
 * @创建日期 2022/10/27 22:16
 */
@Data
public class MailDto implements Serializable {
    private Integer id;
    private Integer stampId;
    private String msg;
    private Integer userId;
    private Integer isDelete;
    private String title;
}
