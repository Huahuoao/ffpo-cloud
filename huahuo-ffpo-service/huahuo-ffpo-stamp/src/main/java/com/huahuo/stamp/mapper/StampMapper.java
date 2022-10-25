package com.huahuo.stamp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.stamp.pojos.Stamp;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
* @author Administrator
* @description 针对表【stamp(邮票)】的数据库操作Mapper
* @createDate 2022-10-25 10:47:20
* @Entity generator.domain.Stamp
*/
@Mapper
public interface StampMapper extends BaseMapper<Stamp> {

}




