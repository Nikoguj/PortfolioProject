package com.portfolio.project.service;

import com.portfolio.project.domain.user.SessionKey;
import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.repository.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldSendEmail() throws UserNotFoundException {
        //Given
        Users users = new Users();

        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("userSessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(1000));
        users.setSessionKey(sessionKey);

        UsersMail usersMail = new UsersMail();
        usersMail.setMail("Nikoguj@gmail.com");
        usersMail.generatePinConfirm();
        users.setUsersMail(usersMail);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(usersMail.getMail());
        mailMessage.setSubject(EMAIL_VERIFICATION_SUBJECT);
        mailMessage.setText("Nice to welcome you on my website, an email verification code: \n" + usersMail.getPinConfirmMail());

        //When
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(users));
        emailService.sendVerificationEmail(users.getSessionKey().getSessionKey(), 1L);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

}