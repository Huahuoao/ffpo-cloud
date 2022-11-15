package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Mail;
import generator.service.MailService;
import generator.mapper.MailMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【mail】的数据库操作Service实现
* @createDate 2022-11-15 10:08:26
*/
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail>
    implements MailService{

}




