package com.ffpo.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.mail.pojos.CollectMail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author huahuo
* @description 针对表【collect_mail】的数据库操作Mapper
* @createDate 2022-11-20 15:19:12
* @Entity generator.domain.CollectMail
*/
@Mapper
public interface CollectMailMapper extends BaseMapper<CollectMail> {
    @Select("select mail_id from collect_mail where user_id = #{id}")
    public List<Integer> listIds(Integer id);
}




