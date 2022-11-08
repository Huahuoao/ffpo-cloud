package com.huahuo.shop.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.PageRequestDto;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.shop.pojos.FreeShop;

import java.util.List;

/**
* @author Administrator
* @description 针对表【free_shop】的数据库操作Service
* @createDate 2022-11-08 13:36:32
*/
public interface FreeShopService extends IService<FreeShop> {
    ResponseResult listFreeShops(PageRequestDto dto);
}
