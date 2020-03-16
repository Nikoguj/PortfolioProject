package com.portfolio.project.scheduler;

import com.portfolio.project.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 12 * * *")
    public void sendDailyMail() {
        emailService.sendDailyMail();
    }
}