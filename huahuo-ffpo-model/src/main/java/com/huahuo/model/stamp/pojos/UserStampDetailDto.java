package com.huahuo.model.stamp.pojos;

import lombok.Data;

/**
 * @作者 花火
 * @创建日期 2022/10/25 22:47
 */
@Data
public class UserStampDetailDto {
    Integer userId;
    Integer stampTypeId;
    String signature;
}
