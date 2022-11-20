package com.ffpo.mail.controller;


import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.mail.dtos.MailSquareLikeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/square")
public class MailSquareController {
    @PostMapping("/like")
    public ResponseResult like(@RequestBody MailSquareLikeDto dto ){

        return null;
    }
}
