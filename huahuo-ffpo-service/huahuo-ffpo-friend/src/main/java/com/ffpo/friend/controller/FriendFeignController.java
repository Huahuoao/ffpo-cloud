package com.ffpo.friend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ffpo.friend.service.FriendService;
import com.huahuo.feign.UserFeignService;
import com.huahuo.model.friend.dtos.FriendIDto;
import com.huahuo.model.friend.pojos.Friend;
import com.huahuo.model.user.pojos.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2022/11/18 20:26
 */
@Slf4j
@RestController
@RequestMapping("/friend/feign")
public class FriendFeignController {
    @Autowired
    FriendService friendService;
    @Autowired
    UserFeignService userFeignService;
    @PostMapping("/become")
    public void becomeFriend(@RequestBody FriendIDto friendIDto) {
        Integer mailNum = 0;
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Friend> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper.eq(Friend::getUserId, friendIDto.getIdOne()).eq(Friend::getFriendId, friendIDto.getIdTwo());
        queryWrapper2.eq(Friend::getUserId, friendIDto.getIdTwo()).eq(Friend::getFriendId, friendIDto.getIdOne());

        Friend one = friendService.getOne(queryWrapper);
        Friend two = friendService.getOne(queryWrapper2);
        if (one != null) {
            mailNum = one.getMailNum();
            mailNum++;
            one.setMailNum(mailNum);
            one.setFriendType(2);
            friendService.updateById(one);
            two.setMailNum(mailNum);
            two.setFriendType(2);
            log.info("更新好友关系成功");
        } else {
            Friend friend = new Friend();
            Friend friend1 = new Friend();
            friend.setUserId(friendIDto.getIdOne());
            friend.setFriendId(friendIDto.getIdTwo());
            User user = userFeignService.getById(friendIDto.getIdTwo());
            User user1 = userFeignService.getById(friendIDto.getIdOne());
            friend.setFriendHeadImg(user.getHeadImg());
            friend.setFriendName(user.getUsername());
            friend.setMailNum(1);

            friend.setFriendType(1);
            friendService.save(friend);
            friend1.setUserId(friendIDto.getIdTwo());
            friend1.setFriendId(friendIDto.getIdOne());
            friend1.setFriendHeadImg(user1.getHeadImg());
            friend1.setFriendName(user1.getUsername());
            friend1.setFriendType(1);
            friend1.setMailNum(1);
            friendService.save(friend1);

            log.info("新增好友关系成功");
        }

    }
}
