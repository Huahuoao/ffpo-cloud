package com.ffpo.friend.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffpo.friend.mapper.FriendMapper;
import com.ffpo.friend.service.FriendService;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.friend.dtos.ListFriendsDto;
import com.huahuo.model.friend.pojos.Friend;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @author Administrator
* @description 针对表【friend】的数据库操作Service实现
* @createDate 2022-11-18 20:19:18
*/
@Service
@Transactional
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService {

    @Override
    public ResponseResult listFriends(ListFriendsDto listFriendsDto) {
        listFriendsDto.checkParam();
        IPage page = new Page(listFriendsDto.getPage(),listFriendsDto.getSize());
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Friend::getFriendType,listFriendsDto.getFriendType());
        IPage pageResult = page(page,queryWrapper);
        ResponseResult responseResult = new PageResponseResult(listFriendsDto.getPage(), listFriendsDto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }
}




