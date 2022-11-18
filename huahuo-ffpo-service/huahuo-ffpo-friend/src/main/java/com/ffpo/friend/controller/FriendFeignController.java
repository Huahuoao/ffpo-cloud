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

}
