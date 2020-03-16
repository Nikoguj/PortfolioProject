package com.portfolio.project.service;

import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification";

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    public void sendVerificationEmail(String userSessionKey, Long userId) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity())) {
            UsersMail usersMail = user.getUsersMail();
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

    public void sendDailyMail () {
        List<Users> usersList = userRepository.findAll();
        List<UsersMail> usersMailList = usersList.stream().map(Users::getUsersMail).filter(UsersMail::isScheduledMail).collect(Collectors.toList());
        for(UsersMail usersMail: usersMailList) {
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(usersMail.getMail());
                mailMessage.setSubject("Daily Mail");
                mailMessage.setText("Are you going to travel a long distance? Check the weather conditions before you go! \n");
                javaMailSender.send(mailMessage);
            } catch (MailException e) {
                System.out.println(e);
            }

        }

    }
}