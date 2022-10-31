package com.ffpo.mail.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffpo.mail.mapper.MailMapper;
import com.ffpo.mail.service.MailService;
import com.ffpo.mail.service.ShippingMailService;
import com.huahuo.feign.StampFeignService;
import com.huahuo.feign.UserFeignService;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.mail.dtos.MailDto;
import com.huahuo.model.mail.dtos.MailPageDto;
import com.huahuo.model.mail.pojos.Mail;
import com.huahuo.model.mail.pojos.ShippingMail;
import com.huahuo.model.user.pojos.User;
import com.huahuo.utils.common.GPSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private StampFeignService stampFeignService;
   @Autowired
    private UserFeignService userFeignService;
    @Autowired
    private ShippingMailService shippingMailService;

    /**
     * 生成新草稿
     * @param dto
     * @return
     */
    @Override
    public ResponseResult upload(MailDto dto) {
        Mail mail = new Mail();
        mail.setMsg(dto.getMsg());
        mail.setTitle(dto.getTitle());
        mail.setCreteTime(DateUtil.now());
        mail.setSendUserId(dto.getUserId());
        mail.setStampId(dto.getStampId());
        mail.setType(2);
        mail.setIsDelete(0);
        String img = stampFeignService.getStampImg(dto.getStampId());
        mail.setStampImg(img);
        save(mail);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "新草稿生成成功！");
    }

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult list(MailPageDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Mail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mail::getUserId, dto.getUserId())
                .eq(Mail::getType, dto.getBagType())
                .eq(Mail::getIsDelete, 0)
                .orderByDesc(Mail::getCreteTime);
        if (dto.getBagType() == 0) {
            queryWrapper.eq(Mail::getIsSend, 1);
        }
        IPage pageResult = page(page, queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult senMailRandom(Mail mail) {
        //随机抽一个幸运用户出来获取邮件
        Integer id = userFeignService.getRandomUserId();
        ArrayList<String> getList = userFeignService.getGPS(id);

        String longitude2S = getList.get(0);
        Double longitude2  = new Double(longitude2S);

        String latitude2S = getList.get(1);
        Double latitude2  = new Double(latitude2S);

        Integer sendUserId = mail.getSendUserId();
        ArrayList<String> getListSend = userFeignService.getGPS(sendUserId);

        String longitude1S = getListSend.get(0);
        Double longitude1  = new Double(longitude1S);

        String latitude1S = getListSend.get(1);
        Double latitude1 = new Double(latitude1S);

        mail.setGetUserId(id);
        Double distance = GPSUtils.GetDistance(longitude1, latitude1, longitude2, latitude2);
        log.info("INSTANCE=------------------>" + distance + "km");
        Double minDouble = distance * 1.728;
        //送达需要的时间
        int min = minDouble.intValue();
        log.info("Time=------------------>" + min + "min");
        String now = DateUtil.now();
        Date date = DateUtil.parse(now);
        //获取送达时间
        Date dateTime = DateUtil.offsetMinute(date, min);
        String formatDateTime = DateUtil.formatDateTime(dateTime);
        log.info("现在时间==" + DateUtil.now());
        log.info("送达时间==" + dateTime);
        //送出位置
        mail.setSendTime(formatDateTime);
        //送达位置
        mail.setCreteTime(DateUtil.now());
        mail.setType(1);
        String stampImg = stampFeignService.getStampImgAndUpdateLife(mail.getStampId());
        mail.setStampImg(stampImg);
        mail.setUserId(mail.getSendUserId());
        save(mail);
        log.info("mail id=="+mail.getId());
        log.info(mail.toString());
        Map resultMap = new HashMap(3);
        resultMap.put("code", AppHttpCodeEnum.SUCCESS.getCode());
        resultMap.put("sendTime", formatDateTime);
        getStamp(mail);
        User user = userFeignService.getById(sendUserId);
        user.setCoinNum(user.getCoinNum()+100);
        userFeignService.save(user);
        return ResponseResult.okResult(resultMap);
    }

    @Override
    public void getStamp(Mail mail) {
        Mail getMail;
        getMail = ObjectUtil.clone(mail);
        getMail.setType(0);
        getMail.setId(null);
        getMail.setUserId(mail.getUserId());
        save(getMail);
        ShippingMail shippingMail = new ShippingMail();
        shippingMail.setIsSend(0);
        shippingMail.setSendTime(mail.getSendTime());
        shippingMail.setGetId(getMail.getId());
        shippingMail.setSendId(mail.getId());
        shippingMailService.save(shippingMail);

    }
}




