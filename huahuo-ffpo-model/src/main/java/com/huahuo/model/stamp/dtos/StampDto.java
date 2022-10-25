package com.huahuo.model.stamp.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @作者 花火
 * @创建日期 2022/10/25 23:29
 */
@Data
public class StampDto implements Serializable {
    Integer userId;
    String img;
    Integer level;
    String msg;
    String name;
    Double life;
    Integer id;
    LocalDateTime getTime;
}
