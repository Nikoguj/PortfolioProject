package com.portfolio.project.service;

import com.portfolio.project.domain.user.UsersMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(UsersMail usersMail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(usersMail.getMail());
            mailMessage.setSubject(EMAIL_VERIFICATION_SUBJECT);
            mailMessage.setText("Nice to welcome you on my website, an email verification code: \n" + usersMail.getPinConfirmMail());
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            System.out.println(e);
        }
    }
}