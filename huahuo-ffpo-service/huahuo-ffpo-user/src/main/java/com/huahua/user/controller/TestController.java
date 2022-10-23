package com.huahua.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2022/10/23 16:05
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/1")
    public String test()
    {
        return "SUCCESS!";
    }
}
