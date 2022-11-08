package com.huahuo.shop.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.shop.pojos.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author Administrator
* @description 针对表【shop】的数据库操作Mapper
* @createDate 2022-10-31 15:31:59
* @Entity generator.domain.Shop
*/
@Mapper
public interface ShopMapper extends BaseMapper<Shop> {
    @Select("SELECT * FROM shop ORDER BY RAND() LIMIT 10")
    public List<Shop> onSale();
    @Update("update shop set is_onsale = 0 where (1=1)")
    public void shopInit();
}




