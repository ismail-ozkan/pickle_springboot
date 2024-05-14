package com.pickle.pickledemo.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendOtpMail(String to, Integer otp) {
        String subject = "Tek Kullanımlık Şifre (OTP)";
        String text = "Merhaba,\n\n"
                + "Tek Kullanımlık Şifreniz (OTP): " + otp + "\n\n"
                + "Bu şifreyi sadece bir kez kullanabilirsiniz. Lütfen güvenliğiniz için paylaşmayınız.\n\n"
                + "İyi günler.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
