package com.huahuo.stamp.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.stamp.pojos.Stamp;
import com.huahuo.model.stamp.pojos.StampDetail;
import com.huahuo.stamp.mapper.StampMapper;
import com.huahuo.stamp.service.StampDetailService;
import com.huahuo.stamp.service.StampService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @作者 花火
 * @创建日期 2022/10/25 10:50
 */
@RequestMapping("/admin")
@RestController
@Slf4j
public class AdminStampController {
    @Autowired
    private StampDetailService service1;
    @Autowired
    private StampService service;
    @Autowired
    private StampMapper stampMapper;
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

    @PostMapping("/list")
    public List<Stamp> list()
    {
         return service.list();

    }
    @PostMapping("/update")
    public void list(@RequestBody Stamp stamp)
    {
        service.updateById(stamp);

    }
    @PostMapping("/remove")
    public void remove(@RequestBody Stamp stamp)
    {
         LambdaQueryWrapper<Stamp> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.eq(Stamp::getId,stamp.getId());
         stampMapper.delete(queryWrapper);

    }

    @PostMapping("/create")
    public void create(@RequestBody Stamp stamp)
    {
        stamp.setCreateTime(DateUtil.now());
        service.save(stamp);

      log.info(stamp.toString());
    }
}
