package com.huahuo.model.friend.dtos;

import com.huahuo.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @作者 花火
 * @创建日期 2022/11/18 20:22
 */
@Data
public class ListFriendsDto extends PageRequestDto {
    private Integer UserId;
    private Integer FriendType;
}
