package com.zerobase.table_reserve.components;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponents {
    private final JavaMailSender javaMailSender;

    public void sendMailTest() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pty10510@gmail.com");
        message.setSubject("안녕하세요. 제로베이스입니다.");
        message.setText(" 안녕하세요. 제로베이스입니다. 메일테스트입니다.");

        javaMailSender.send(message);
    }

    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setTo(mail);
                messageHelper.setSubject(subject);
                messageHelper.setText(text, true);
            }
        };

        try {
            javaMailSender.send(messagePreparator);
            result = true;
        } catch (Exception e) {
            // TODO
            throw new RuntimeException(e);
        }

        return result;
    }
}
