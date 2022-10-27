package com.ffpo.mail.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huahuo.model.mail.pojos.Mail;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【mail】的数据库操作Mapper
* @createDate 2022-10-27 22:06:32
* @Entity generator.domain.Mail
*/
@Mapper
public interface MailMapper extends BaseMapper<Mail> {

}




