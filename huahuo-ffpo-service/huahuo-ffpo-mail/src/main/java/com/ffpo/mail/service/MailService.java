package com.ffpo.mail.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.*;
import com.huahuo.model.mail.pojos.Mail;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @author Administrator
 * @description 针对表【mail】的数据库操作Service
 * @createDate 2022-10-27 22:06:32
 */
public interface MailService extends IService<Mail> {
    ResponseResult upload(MailDto dto);
    ResponseResult like(@RequestBody MailSquareLikeDto dto );
    ResponseResult list(MailPageDto dto);

    ResponseResult senMailRandom(Mail mail) throws IOException;
    ResponseResult senMailById(Mail mail) throws IOException;
    ResponseResult search(EsSearchDto userSearchDto) throws IOException;
    public void getStamp(Mail mail);
    public ResponseResult listPublicMails(@RequestBody PbMail dto);
}
