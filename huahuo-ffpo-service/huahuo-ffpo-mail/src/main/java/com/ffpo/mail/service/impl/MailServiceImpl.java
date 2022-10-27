package com.ffpo.mail.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffpo.mail.feign.StampFeignService;
import com.ffpo.mail.mapper.MailMapper;
import com.ffpo.mail.service.MailService;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.mail.dtos.MailDto;
import com.huahuo.model.mail.dtos.MailPageDto;
import com.huahuo.model.mail.pojos.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @description 针对表【mail】的数据库操作Service实现
 * @createDate 2022-10-27 22:06:32
 */
@Transactional
@Service
@Slf4j
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail>
        implements MailService {
@Autowired
    StampFeignService stampFeignService;
    @Override
    public ResponseResult upload(MailDto dto) {
        Mail mail = new Mail();
        mail.setMsg(dto.getMsg());
        mail.setCreteTime(DateUtil.now());
        mail.setId(dto.getId());
        mail.setSendUserId(dto.getUserId());
        mail.setStampId(dto.getStampId());
        mail.setType(2);
        mail.setIsDelete(0);
        log.info("-------------------目前正常--------------------------");
        String img = stampFeignService.getStampImg(dto.getStampId());
        log.info("img=------------------------------------------>"+img);
        mail.setStampImg(img);
        save(mail);
        log.info("save success !________________________");
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "新草稿生成成功！");
    }

    @Override
    public ResponseResult list(MailPageDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Mail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mail::getSendUserId, dto.getUserId())
                .eq(Mail::getType, dto.getBagType())
                .eq(Mail::getIsDelete, 0)
                .orderByDesc(Mail::getCreteTime);
        IPage pageResult = page(page,queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }


}




