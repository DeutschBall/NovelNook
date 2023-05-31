package class4.spm.novelnook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    //方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
    public void sendMail(String to, String text) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom("3044782@qq.com");         //须修改为与yaml中配置的邮箱一致
        simpMsg.setTo(to);
        simpMsg.setSubject("Please Return Your Materials");
        simpMsg.setText("These materials are about to expire soon:\n"+text);
        javaMailSender.send(simpMsg);
    }
}
