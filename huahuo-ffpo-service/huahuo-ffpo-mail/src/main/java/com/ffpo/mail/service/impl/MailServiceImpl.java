package com.ffpo.mail.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ffpo.mail.feign.StampFeignService;
import com.ffpo.mail.feign.UserFeignService;
import com.ffpo.mail.mapper.MailMapper;
import com.ffpo.mail.service.MailService;
import com.huahuo.model.common.dtos.PageResponseResult;
import com.huahuo.model.common.dtos.ResponseResult;
import com.huahuo.model.common.enums.AppHttpCodeEnum;
import com.huahuo.model.mail.dtos.MailDto;
import com.huahuo.model.mail.dtos.MailPageDto;
import com.huahuo.model.mail.pojos.Mail;
import com.huahuo.utils.common.GPSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String img = stampFeignService.getStampImg(dto.getStampId());
        mail.setStampImg(img);
        save(mail);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS.getCode(), "新草稿生成成功！");
    }

    @Override
    public ResponseResult list(MailPageDto dto) {
        dto.checkParam();
        IPage page = new Page(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<Mail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mail::getUserId, dto.getUserId())
                .eq(Mail::getType, dto.getBagType())
                .eq(Mail::getIsDelete, 0)
                .orderByDesc(Mail::getCreteTime);
        if(dto.getBagType()==0)
        {
            queryWrapper.eq(Mail::getIsSend,1);
        }
        IPage pageResult = page(page, queryWrapper);
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }

    @Override
    public ResponseResult senMailRandom(Mail mail) {
        Integer id = userFeignService.getRandomUserId();
        Map<String, Double> getMap = userFeignService.getGPS(id);
//        Double longitude2 = getMap.get("longitude");
//        Double latitude2 = getMap.get("latitude");
        Integer sendUserId = mail.getSendUserId();
        Map<String, Double> sendMap = userFeignService.getGPS(sendUserId);
//        Double longitude1 = sendMap.get("longitude");
//        Double latitude1 =  sendMap.get("latitude");
        Double longitude1 = 119.27345;
        Double latitude1 = 26.04769;
        Double longitude2 = 121.48941;
        Double latitude2 = 31.40527;
        mail.setGetUserId(id);
        Double distance = GPSUtils.GetDistance(longitude1, latitude1, longitude2, latitude2);
        log.info("INSTANCE=------------------>" + distance + "km");
        Double minDouble = distance * 6.0;
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
        String stampImg = stampFeignService.getStampImg(mail.getStampId());
        mail.setStampImg(stampImg);
        mail.setUserId(mail.getSendUserId());
        save(mail);
        log.info(mail.toString());
        Map resultMap = new HashMap(3);
        resultMap.put("code", AppHttpCodeEnum.SUCCESS.getCode());
        resultMap.put("sendTime", formatDateTime);
        getStamp(mail);
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

    }
}




