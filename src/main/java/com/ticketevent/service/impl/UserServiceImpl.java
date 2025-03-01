package com.ticketevent.service.impl;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.event.RegistrationCompleteEvent;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ticketevent.constant.Constants.INVALID_TOKEN_MESSAGE;
import static com.ticketevent.constant.Constants.TOKEN_ALREADY_EXPIRED;
import static com.ticketevent.util.UrlUtils.applicationUrl;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements IUserService {
    final IUserRepository userRepository;
    final PasswordEncoder passwordEncoder;
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
    public void createUserAdmin(UserEntity user) {
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("O email ".concat(user.getEmail()) + " já existe");
        }
        if (getUserByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new DataIntegrityViolationException("O número de telefone".concat(user.getPhoneNumber()) + "já existe");
        }

        //user.setRoles(ERole.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void createUserOrganizer(UserEntity user, HttpServletRequest request) {

        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("O email ".concat(user.getEmail()) + " já existe");
        }
        if (getUserByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new DataIntegrityViolationException("O número de telefone".concat(user.getPhoneNumber()) + "já existe");
        }

        //user.setRoles(ERole.ORGANIZER);
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



