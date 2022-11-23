import com.ffpo.mail.service.MailService;
import com.ffpo.mail.service.impl.MailServiceImpl;
import com.huahuo.model.mail.pojos.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @作者 花火
 * @创建日期 2022/11/23 8:59
 */

@SpringBootTest
@RunWith(JUnit4.class)
public class test {

    MailService mailService = new MailServiceImpl();

    @Test
    public void test(){
        Mail mail = new Mail();
        mail.setTitle("123");
mailService.save(mail);
        System.out.println(mail.getId());
    }
}
