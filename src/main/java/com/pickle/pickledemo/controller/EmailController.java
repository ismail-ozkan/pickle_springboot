package com.pickle.pickledemo.controller;

import com.pickle.pickledemo.dto.EmailDto;
import com.pickle.pickledemo.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/sendTestEmail")
    public void sendTestEmail() {
        emailService.sendSimpleMessage("ismailozkanlaw@gmail.com", "Test Mail", "Test email body");
    }

    @PostMapping("/sendEmailWithAttach")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestPart String mailAddress, @RequestPart MultipartFile file) {
        try {
            InputStreamSource attachmentSource = new ByteArrayResource(file.getBytes());
            emailService.sendEmailWithAttachment(mailAddress, attachmentSource, file.getOriginalFilename());
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException | IOException e) {
            return ResponseEntity.badRequest().body("Error while sending email: " + e.getMessage());
        }
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailDto emailDto) {
        try {
            emailService.sendSimpleMessage(emailDto.getTo(), emailDto.getSubject(), emailDto.getText());
            return ResponseEntity.ok("Email sent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error while sending email: " + e.getMessage());
        }

    }

}
