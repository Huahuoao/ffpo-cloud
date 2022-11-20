package com.huahuo.stamp.controller;

import com.huahuo.model.mail.pojos.Mail;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.stamp.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2022/10/28 22:47
 */
@RequestMapping("/admin")
@RestController
public class FeignStampController {
    @Autowired
    private StampDetailService service1;
    @Autowired
    private StampService service;
    @GetMapping("/feign/getstampimg/{id}")
    public String getStampImgAndUpdateLife(@PathVariable("id") Integer id)
    {
        StampDetail byId = service1.getById(id);
        double life = byId.getLife();
        life-=0.05;
        life = (double) Math.round(life*100)/100;
        byId.setLife(life);
        service1.updateById(byId);
        return byId.getImg();
    }
    @GetMapping("/feign/getstamp/detail/{id}")
    public StampDetail getStampDetail(@PathVariable("id") Integer id)
    {
      return service1.getById(id);
    }
    @GetMapping("/feign/getstamp/simple/{id}")
    public Stamp getStamp(@PathVariable("id") Integer id)
    {
        Stamp byId = service.getById(id);
        return byId;
    }
    @GetMapping("/feign/getstampimg/simple/{id}")
    public String getStampImg(@PathVariable("id") Integer id)
    {
        Stamp byId = service.getById(id);
        return byId.getImg();
    }


    @PostMapping("/feign/save")
    public void saveStamptoUser(@RequestBody StampDetail stampDetail)
    {
        service1.save(stampDetail);
    }
}
