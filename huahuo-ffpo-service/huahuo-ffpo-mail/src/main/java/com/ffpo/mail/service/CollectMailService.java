package com.ffpo.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.pojos.CollectMail;
import com.huahuo.model.mail.pojos.Mail;
import io.swagger.models.auth.In;

import java.io.IOException;
import java.net.ResponseCache;

/**
* @author huahuo
* @description 针对表【collect_mail】的数据库操作Service
* @createDate 2022-11-20 15:19:12
*/
public interface CollectMailService extends IService<CollectMail> {
    ResponseResult listCollectedMails(Integer id);

}
