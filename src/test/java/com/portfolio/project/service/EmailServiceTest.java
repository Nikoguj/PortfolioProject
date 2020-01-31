package com.portfolio.project.service;

import com.portfolio.project.domain.user.UsersMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("Nikoguj@gmail.com");
        usersMail.generatePinConfirm();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usersMail.getMail());
        mailMessage.setSubject(EMAIL_VERIFICATION_SUBJECT);
        mailMessage.setText("Nice to welcome you on my website, an email verification code: \n" + usersMail.getPinConfirmMail());

        //When
        emailService.sendVerificationEmail(usersMail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

}