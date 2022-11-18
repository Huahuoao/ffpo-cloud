package com.huahua.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.user.pojos.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2022-10-23 10:48:36
 * @Entity generator.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user ORDER BY RAND() LIMIT 1")
    public User getRandonId();

}




