package com.huahuo.shop.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.model.common.dtos.PageRequestDto;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.shop.pojos.FreeShop;
import com.huahuo.shop.mapper.FreeShopMapper;
import com.huahuo.shop.service.FreeShopService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【free_shop】的数据库操作Service实现
* @createDate 2022-11-08 13:36:32
*/
@Service
public class FreeShopServiceImpl extends ServiceImpl<FreeShopMapper, FreeShop>
    implements FreeShopService {

    @Override
    public  ResponseResult  listFreeShops(PageRequestDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<FreeShop> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(FreeShop::getPrice);
        IPage pageResult = page(page,queryWrapper);
        ResponseResult responseResult =new PageResponseResult(dto.getPage(),dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());

        return responseResult;
    }
}




