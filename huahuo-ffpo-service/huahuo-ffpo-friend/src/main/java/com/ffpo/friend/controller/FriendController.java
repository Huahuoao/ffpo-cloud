package com.ffpo.friend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ffpo.friend.service.FriendService;
import com.huahuo.feign.UserFeignService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.friend.dtos.FriendIDto;
import com.huahuo.model.friend.dtos.ListFriendsDto;
import com.huahuo.model.friend.pojos.Friend;
import com.huahuo.model.user.pojos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2022/11/18 20:20
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private UserFeignService userFeignService;
    @PostMapping("/list")
    public ResponseResult listFriends(@RequestBody ListFriendsDto listFriendsDto)
    {
        return friendService.listFriends(listFriendsDto);
    }

    @GetMapping("/test")
    public String iji(){
        return "123";
    }

}
