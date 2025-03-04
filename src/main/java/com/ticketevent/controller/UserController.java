package com.ticketevent.controller;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.request.UserCreateRequestDto;
import com.ticketevent.entity.dto.response.UserResponse;
import com.ticketevent.entity.token.VerificationTokenEntity;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.repository.IVerificationTokenRepository;
import com.ticketevent.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.ticketevent.constant.Constants.*;
import static com.ticketevent.util.Helper.stringToUUID;
import static com.ticketevent.util.UrlUtils.addIdToCurrentUrlPath;


@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "utilizador")
@RequestMapping("/user")
@CrossOrigin(origins = "*")
//@EnableMethodSecurity
public class UserController {

    final IUserService userService;
    final IVerificationTokenRepository verificationTokenRepository;


    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(UserResponse::new).toList());


    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<UserEntity> findById(@PathVariable String userId) {
        Optional<UserEntity> user = userService.getUserById(stringToUUID(userId));
        return ResponseEntity.ok((user).orElseThrow(() -> new ObjectNotFoundException(USER_NOT_FOUND_MESSAGE)));
    }


    @PostMapping("/save")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserCreateRequestDto request, final HttpServletRequest httpRequest) {
        try {
            var user = new UserEntity();
            BeanUtils.copyProperties(request, user);
            userService.createUser(user, httpRequest);
            URI uri = addIdToCurrentUrlPath(user.getUserId().toString());

            return ResponseEntity.created(uri).body(USER_SAVE_SUCCESSFULLY_MESSAGE);
        } catch (DataIntegrityViolationException e) {
            log.error(ERROR_ON_CREATE_USER, e);
            throw new DataIntegrityViolationException(USER_ALREADY_EXIST_MESSAGE);

        }
    }


    @GetMapping("/verifyEmail/account")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationTokenEntity theToken = verificationTokenRepository.findByToken(token);
        if (theToken.getUser().isEnabled()){
            return ACCOUNT_ALREADY_VERIFIED;
        }
        String verificationResult = userService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            return ACCOUNT_SUCCESSFULLY_VERIFIED_MESSAGE;
        }
        return INVALID_TOKEN_MESSAGE;
    }

    @GetMapping("/resendVerificationToken")
   // @PreAuthorize("hasRole('USER')")
    public String resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request){
        var verificationToken = userService.generateNewVerificationToken(oldToken);
        var user = verificationToken.getUser();
       // mailService.resendVerificationEmail(user, applicationUrl(request), verificationToken);
        return NEW_VERIFICATION_TOKEN_MESSAGE;


    }





}
