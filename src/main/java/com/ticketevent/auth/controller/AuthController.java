package com.ticketevent.auth.controller;

import com.ticketevent.auth.service.IAuthService;
import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;
import com.ticketevent.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.Instant.now;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {
    final JwtEncoder jwtEncoder;
    final IUserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;
    final IAuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {

        AuthResponseDto authResponse = authService.authenticate(authRequestDto);


    return ResponseEntity.ok(authResponse);
    }
}





