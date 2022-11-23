package com.ffpo.mail.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.mail.dtos.EsMailDto;
import com.huahuo.model.mail.pojos.Mail;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【mail】的数据库操作Mapper
* @createDate 2022-10-27 22:06:32
* @Entity generator.domain.Mail
*/
@Mapper
public interface MailMapper extends BaseMapper<Mail> {

    @Select("select id,send_time,stamp_img,send_user_id,title,tags,msg from mail where is_send = 1")
    public List<EsMailDto>  esList();

    @Select("select * from mail where is_send = 1 and is_public = 1")
    public List<Mail> listPbMail();
    @Select("SELECT LAST_INSERT_ID()")
    public Integer SelectLastId();


}




