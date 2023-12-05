package com.booking.project.utils.email;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailSender implements IEmailSender {
    @Value(value= "${spring.mail.username}")
    private String email;
    @Autowired
    private JavaMailSender mailSender;
    @Async
    @Override
    public void send(String to, String content) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(content,true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(email);
            mailSender.send(mimeMessage);
        }catch (Exception e){
            throw new IllegalStateException("failed to sent email");
        }
    }
}
