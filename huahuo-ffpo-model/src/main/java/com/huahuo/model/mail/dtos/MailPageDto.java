package com.huahuo.model.mail.dtos;

import com.huahuo.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @作者 花火
 * @创建日期 2022/10/27 22:29
 */
@Data
public class MailPageDto extends PageRequestDto implements Serializable {
    private Integer userId;
    private Integer bagType;
}
