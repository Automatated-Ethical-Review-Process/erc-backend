package com.g7.ercsystem.utils;

import com.g7.ercsystem.configuration.MailConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class MailSender {

    @Autowired
    private MailConfiguration configuration ;

    public void sendVerificationEmail(String name,String email,String url ) throws MessagingException, UnsupportedEncodingException {

        String fromAddress = "harshanadun52@gmail.com";
        String senderName = "ERC University of Ruhuna";
        String subject = "Please verify your subscription account";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a>VERIFY</a></h3>"
                + "Thank you,<br>"
                + "ERC UOR <br>";
        System.out.println("Init");
        MimeMessage mimeMessage = configuration.getJavaMailSender().createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(fromAddress,senderName);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);

        content = content.replace("[[name]]",name);

        mimeMessageHelper.setText(content,true);
        System.out.println("Start sending");

        configuration.getJavaMailSender().send(mimeMessage);
        System.out.println("Email has been sent");
    }
}
