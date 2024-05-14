package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.service.impl.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;


    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/email")
    public void sendTestEmail() {
        emailService.sendSimpleMessage("ismailozkanlaw@gmail.com", "Test Mail", "Test email body");
    }
}
