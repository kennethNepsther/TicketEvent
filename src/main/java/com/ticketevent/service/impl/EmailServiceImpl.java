package com.ticketevent.service.impl;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.token.VerificationTokenEntity;
import com.ticketevent.service.IEmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.ticketevent.constant.Constants.*;
import static com.ticketevent.util.EmailUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

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
            mailSender.send(message);

        }catch (Exception e) {
            log.error("Unable to send  email: {}", e.getMessage());
            throw new RuntimeException(ERROR_SENDING_VERIFICATION_EMAIL);

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
            mailSender.send(message);

        }catch (Exception e) {
            throw new RuntimeException(ERROR_SENDING_VERIFICATION_EMAIL);

        }

    }

    @Async
    @Override
    public void sendNewAccountVerificationEmail(String name, String email, String token) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(message);
            messageHelper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(email);
            messageHelper.setText(mailContentVerification(name, host, token),true);
            mailSender.send(message);

        }catch (Exception e) {
            log.error("Error sending verification  email: {}", e.getMessage());
            throw new RuntimeException(ERROR_SENDING_VERIFICATION_EMAIL);

        }

    }

    @Override
    public void resendVerificationEmail(UserEntity user, String applicationUrl, VerificationTokenEntity verificationToken) {
            String verificationUrl = applicationUrl + "/user/verifyEmail/account?token="+verificationToken;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(message);
            messageHelper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            messageHelper.setFrom(fromEmail);
            messageHelper.setTo(user.getEmail());
            messageHelper.setText(mailContentVerification(user.getFirstName(), host, verificationUrl),true);
            mailSender.send(message);

        }catch (Exception e) {

            throw new RuntimeException(ERROR_SENDING_VERIFICATION_EMAIL);

        }

    }


}













