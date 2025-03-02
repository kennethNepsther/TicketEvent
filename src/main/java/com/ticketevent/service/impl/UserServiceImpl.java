package com.ticketevent.service.impl;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.ERole;
import com.ticketevent.event.RegistrationCompleteEvent;
import com.ticketevent.repository.IRoleRepository;
import com.ticketevent.repository.IUserRepository;
import com.ticketevent.repository.IVerificationTokenRepository;
import com.ticketevent.service.IUserService;
import com.ticketevent.entity.token.VerificationTokenEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ticketevent.constant.Constants.*;
import static com.ticketevent.util.UrlUtils.applicationUrl;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements IUserService {
    final IUserRepository userRepository;
    final IRoleRepository roleRepository;
    final BCryptPasswordEncoder passwordEncoder;
    final IVerificationTokenRepository tokenRepository;
    final ApplicationEventPublisher publisher;


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(UUID userId) {
        return userRepository.findUserEntityByUserId(userId);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email.toLowerCase());
    }

    @Override
    public Optional<UserEntity> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserEntityByPhoneNumber(phoneNumber);
    }

    @Override
    public void createAdmin(UserEntity userAdmin) {
        if (getUserByEmail(userAdmin.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException(USER_ALREADY_EXISTS_WITH_EMAIL);
        }
        if (getUserByPhoneNumber(userAdmin.getPhoneNumber()).isPresent()) {
            throw new DataIntegrityViolationException(USER_ALREADY_EXISTS_WITH_EMAIL);
        }
        var roleUser = roleRepository.findByName(ERole.ADMIN.name());
        userAdmin.setRoles(Set.of(roleUser));
        userAdmin.setPassword(passwordEncoder.encode(userAdmin.getPassword()));
        userRepository.save(userAdmin);

    }

    @Override
    public void createUser(UserEntity user, HttpServletRequest request) {

        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException(USER_ALREADY_EXISTS_WITH_EMAIL);
        }
        if (getUserByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new DataIntegrityViolationException(USER_ALREADY_EXISTS_WITH_PHONE);
        }
        var roleUser = roleRepository.findByName(ERole.USER.name());

        user.setRoles(Set.of(roleUser));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        userRepository.save(user);

    }



    @Override
    public void saveUserVerificationToken(UserEntity user, String token) {
        var verificationToken = new VerificationTokenEntity(token, user);
        tokenRepository.save(verificationToken);

    }

    @Override
    public String validateToken(String theToken) {
        VerificationTokenEntity token = tokenRepository.findByToken(theToken);
        if (token == null) {
            return INVALID_TOKEN_MESSAGE;
        }
        var user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(token);
            return TOKEN_ALREADY_EXPIRED;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public VerificationTokenEntity generateNewVerificationToken(String oldToken) {

        var oldTokenToRenew = tokenRepository.findByToken(oldToken);
        var verificationTokenTime = new VerificationTokenEntity();
        oldTokenToRenew.setToken(UUID.randomUUID().toString());
        oldTokenToRenew.setExpirationTime(verificationTokenTime.getExpirationTime());
        return tokenRepository.save(oldTokenToRenew);
    }


}



