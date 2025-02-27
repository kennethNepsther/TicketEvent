package com.ticketevent.service;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.token.VerificationTokenEntity;

public interface IEmailService {
    void sendPasswordResetEmail(String name, String email, String token);
    void sendNewAccountEmail(String name, String email, String token);

    void sendNewAccountVerificationEmail(String name, String email, String token);

    void resendVerificationEmail(UserEntity user, String applicationUrl, VerificationTokenEntity verificationToken);
}

