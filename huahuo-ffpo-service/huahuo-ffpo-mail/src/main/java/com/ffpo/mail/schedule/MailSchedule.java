package com.ffpo.mail.schedule;

import cn.hutool.core.date.DateUtil;
import com.ffpo.mail.service.MailService;
import com.ffpo.mail.service.ShippingMailService;
import com.huahuo.model.mail.pojos.Mail;
import com.huahuo.model.mail.pojos.ShippingMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 花火
 * @创建日期 2022/10/28 21:56
 */
@Component
@Slf4j
public class MailSchedule {
    @Autowired
    private MailService mailService;
    @Autowired
    private ShippingMailService shippingMailService;
    //每1分钟执行一次
    @Scheduled(cron = "0 0/1 * * * ?" )
    public void updateMailDS()
    {
        List<ShippingMail> list = shippingMailService.list();
        for (ShippingMail shippingMail : list) {
            String date1 = shippingMail.getSendTime();
            String date2 = DateUtil.now();
            if(date1.compareTo(date2)==-1){
                Mail getMail = mailService.getById(shippingMail.getGetId());
                Mail sendMail = mailService.getById(shippingMail.getSendId());
                getMail.setIsSend(1);
                sendMail.setIsSend(1);
                log.info("邮件已送达！！ 邮件ID："+sendMail.getId()+"送达时间为: "+DateUtil.now());
                mailService.updateById(getMail);
                mailService.updateById(sendMail);
                shippingMailService.removeById(shippingMail);

            }
        }
        log.info("更新邮件成功！当前时间为===>"+DateUtil.now());
    }
}
