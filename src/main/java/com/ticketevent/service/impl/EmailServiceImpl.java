package com.ticketevent.service.impl;

import com.ticketevent.service.IEmailService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.ticketevent.constant.Constants.NEW_USER_ACCOUNT_VERIFICATION;
import static com.ticketevent.constant.Constants.PASSWORD_RESET_REQUEST;
import static com.ticketevent.util.EmailUtils.getEmailMessage;
import static com.ticketevent.util.EmailUtils.getResetPasswordMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender sender;

    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;


    @Async
    @Override
    public void sendPasswordResetEmail(String name, String email, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getResetPasswordMessage(name, host, token));
            sender.send(message);

        }catch (Exception e) {
            log.error("Unable to send  email: {}", e.getMessage());
            throw new RuntimeException("Error to sending reset password email");

        }

    }
    @Async
    @Override
    public void sendNewAccountEmail(String name, String email, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            sender.send(message);

        }catch (Exception e) {
            log.error("Error sending verification  email: {}", e.getMessage());
            throw new RuntimeException("Error sending verification email");

        }

    }
}
