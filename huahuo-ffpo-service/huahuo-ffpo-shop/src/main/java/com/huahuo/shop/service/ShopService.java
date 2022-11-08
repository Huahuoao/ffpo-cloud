package com.huahuo.shop.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.PageRequestDto;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.shop.dtos.ShoppingDto;
import com.huahuo.model.shop.pojos.FreeShop;
import com.huahuo.model.shop.pojos.Shop;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【shop】的数据库操作Service
 * @createDate 2022-10-31 15:31:59
 */
public interface ShopService extends IService<Shop> {
    /**
     * 每日定时更新商店
     */
    void onSaleSchedule();
    ResponseResult listStampShops(PageRequestDto dto);

   ResponseResult shopping(ShoppingDto dto) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;
}
