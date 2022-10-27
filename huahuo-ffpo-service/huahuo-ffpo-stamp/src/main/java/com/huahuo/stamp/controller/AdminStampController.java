package com.huahuo.stamp.controller;

import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.stamp.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @作者 花火
 * @创建日期 2022/10/25 10:50
 */
@RequestMapping("/admin")
@RestController
public class AdminStampController {
    @Autowired
    private StampService service;
    @PostMapping("/upload/img")
    public ResponseResult uploadimg(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseResult.okResult( service.uploadImg(file));
    }

   @PostMapping("/upload")
    public ResponseResult uploadstamp(  @RequestBody Stamp stamp)
   {
       return ResponseResult.okResult(service.uploadStamp(stamp));
   }

    @PostMapping("/delete")
    public ResponseResult deletestamp(Integer id)
    {
         service.removeById(id);
         return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(),"删除成功");
    }

    @GetMapping("/feign/getstampimg/{id}")
    public String getStampImg(@PathVariable("id") Integer id)
    {
        Stamp byId = service.getById(id);
        return byId.getImg();
    }
}
