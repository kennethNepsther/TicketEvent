package com.ticketevent.service;

public interface IEmailService {
    void sendPasswordResetEmail(String name, String email, String token);
    void sendNewAccountEmail(String name, String email, String token);

    void sendNewAccountVerificationEmail(String name, String email, String token);


}
