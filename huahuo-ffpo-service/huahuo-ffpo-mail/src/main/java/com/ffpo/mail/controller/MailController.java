package com.ffpo.mail.controller;

import com.ffpo.mail.service.MailService;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.*;
import com.huahuo.model.mail.pojos.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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
     *
     * @param
     * @return
     */
    @CacheEvict(value = "mailPageCacahe", allEntries = true)
    @PostMapping("/upload/draft")
    public ResponseResult uploadByDraft(@RequestBody MailDto dto) {
        return service.upload(dto);
    }

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @Cacheable(value = "mailPageCacahe", key = "#dto.userId+'_'+#dto.bagType+'_'+#dto.page+'_'+#dto.size")
    public ResponseResult list(@RequestBody MailPageDto dto) {
        return service.list(dto);

    }

    /**
     * 随机发送信件
     * @param mail
     * @return
     * @throws IOException
     */
    @PostMapping("/send/random")
    public ResponseResult senMailRandom(@RequestBody Mail mail) throws IOException {
        return service.senMailRandom(mail);
    }
    @PostMapping("/send/id")
    public ResponseResult senMailById(@RequestBody Mail mail) throws IOException {
        return service.senMailById(mail);
    }

    @GetMapping("/like/1/{id}")
    public ResponseResult like(@PathVariable("id") Integer id) {
        Mail stamp = service.getById(id);
        if (stamp.getIsLike() == 0) {
            stamp.setIsLike(1);
        }
        return ResponseResult.okResult("收藏成功！");
    }

    /**
     * 取消收藏
     *
     * @param id
     * @return
     */
    @GetMapping("/like/0/{id}")
    public ResponseResult unlike(@PathVariable("id") Integer id) {
        Mail stamp = service.getById(id);
        if (stamp.getIsLike() == 1) {
            stamp.setIsLike(0);
        }
        return ResponseResult.okResult("取消成功！");
    }



    @PostMapping("/search")
    public ResponseResult search(@RequestBody EsSearchDto dto) throws IOException {
        return service.search(dto);
    }

    @PostMapping("list/pbmail")
    public ResponseResult listPublicMails(@RequestBody PbMail dto)

    {
           return service.listPublicMails(dto);
    }
}


