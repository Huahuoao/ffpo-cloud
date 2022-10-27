package com.huahuo.model.stamp.dtos;

import com.huahuo.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.io.Serializable;


/**
 * @作者 花火
 * @创建日期 2022/10/25 23:29
 */
@Data
public class StampPageDto extends PageRequestDto implements Serializable {
    Integer orderWay;
    Integer userId;
}
