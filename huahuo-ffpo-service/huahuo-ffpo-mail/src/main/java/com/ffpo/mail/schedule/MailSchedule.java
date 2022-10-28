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
    @Scheduled(cron = "0 0/30 * * * ?" )
    public void updateMailDS()
    {
        List<ShippingMail> list = shippingMailService.list();
        for (ShippingMail shippingMail : list) {
            String date1 = shippingMail.getSendTime();
            String date2 = DateUtil.now();
            if(date1.compareTo(date2)==-1){
                Mail byId = mailService.getById(shippingMail.getId());
                byId.setIsSend(1);
                mailService.updateById(byId);
                shippingMailService.removeById(shippingMail);
            }
        }
    }
}
