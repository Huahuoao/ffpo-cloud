package com.huahuo.model.stamp.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * @作者 花火
 * @创建日期 2022/10/25 22:47
 */
@Data
public class UserStampDetailDto implements Serializable {
    Integer id;
    Integer stampTypeId;
    String signature;
}
