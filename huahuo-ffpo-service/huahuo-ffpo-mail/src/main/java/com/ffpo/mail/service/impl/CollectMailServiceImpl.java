package com.ffpo.mail.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ffpo.mail.mapper.CollectMailMapper;
import com.ffpo.mail.service.CollectMailService;
import com.ffpo.mail.service.MailService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.pojos.CollectMail;
import com.huahuo.model.mail.pojos.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author huahuo
* @description 针对表【collect_mail】的数据库操作Service实现
* @createDate 2022-11-20 15:19:12
*/
@Service
public class CollectMailServiceImpl extends ServiceImpl<CollectMailMapper, CollectMail>
    implements CollectMailService {
@Autowired
private CollectMailMapper collectMailMapper;
@Autowired
private MailService mailService;
    @Override
    public ResponseResult listCollectedMails(Integer id) {
   List<Integer> ids = collectMailMapper.listIds(id); //传入用户id查询收藏邮件id
        List<Mail> mails = new ArrayList<>();
        for (Integer mailId : ids) {
            Mail mail = mailService.getById(mailId);
            mails.add(mail);
        }
        LambdaQueryWrapper<Mail> queryWrapperTwo = new LambdaQueryWrapper<>();
        return ResponseResult.okResult(mails);
    }
}




