package com.huahuo.model.mail.dtos;

import com.huahuo.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @作者 花火
 * @创建日期 2022/11/19 13:56
 */
@Data
public class PbMail extends PageRequestDto {
    // 1 时间排序 2 喜欢数量排序
    private Integer orderWay;
}
