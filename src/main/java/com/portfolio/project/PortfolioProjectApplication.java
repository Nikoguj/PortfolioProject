package com.portfolio.project;

import com.portfolio.project.google.service.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class PortfolioProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioProjectApplication.class, args);
	}

}
