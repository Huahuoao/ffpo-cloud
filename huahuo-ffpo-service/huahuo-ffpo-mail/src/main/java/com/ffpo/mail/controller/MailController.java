package com.ffpo.mail.controller;

import com.ffpo.mail.service.MailService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.MailDto;
import com.huahuo.model.mail.dtos.MailPageDto;
import com.huahuo.model.mail.pojos.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2022/10/27 22:09
 */
@RestController
@RequestMapping("/api")
public class MailController {
    @Autowired
    MailService service;
    /**
     * 保存为草稿
     * @param
     * @return
     */
    @CacheEvict(value = "mailPageCacahe",allEntries = true)
    @PostMapping("/upload/draft")
    public ResponseResult uploadByDraft(@RequestBody MailDto dto){
        return service.upload(dto);
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Cacheable(value = "mailPageCacahe",key = "#dto.userId+'_'+#dto.bagType+'_'+#dto.page+'_'+#dto.size")
    public ResponseResult list(@RequestBody MailPageDto dto)
    {
        return service.list(dto);

    }

@PostMapping("/send/random")
    public ResponseResult senMailRandom(@RequestBody Mail mail)
{
    return service.senMailRandom(mail);
}





}
