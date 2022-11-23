package com.huahuo.feign;

import com.huahuo.model.friend.dtos.FriendIDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @作者 花火
 * @创建日期 2022/11/18 20:35
 */
@FeignClient(name = "huahuo-ffpo-friend", path = "/friend/feign")
@Component
public interface FriendFeignService {
    @PostMapping("/become")
    public void becomeFriend(@RequestBody FriendIDto friendIDto) ;
}
