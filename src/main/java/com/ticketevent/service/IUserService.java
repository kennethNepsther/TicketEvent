package com.ticketevent.service;

import com.ticketevent.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(UUID userId);
    Optional<UserEntity> getUserByEmail(String email);
    Optional<UserEntity> getUserByPhoneNumber(String phoneNumber);
    void createAdmin(UserEntity user);
    void createUser(UserEntity user, final HttpServletRequest request);
    void saveUserVerificationToken(UserEntity user, String verificationToken);
    String validateToken(String theToken);
}
