package com.whatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.whatsapp.repository"})
@EntityScan({"com.whatsapp.model"})
@EnableJpaAuditing(
        auditorAwareRef = "auditAwareImpl"
)
public class WhatsappApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhatsappApplication.class, args);
    }
}