package com.ffpo.friend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.friend.dtos.ListFriendsDto;
import com.huahuo.model.friend.pojos.Friend;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author Administrator
* @description 针对表【friend】的数据库操作Service
* @createDate 2022-11-18 20:19:18
*/
public interface FriendService extends IService<Friend> {
    public ResponseResult listFriends(@RequestBody ListFriendsDto listFriendsDto);
}
