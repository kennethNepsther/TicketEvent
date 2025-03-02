package com.ticketevent.controller;

import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;
import com.ticketevent.service.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    final IAuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto authRequestDto) {

        AuthResponseDto authResponse = authService.authenticate(authRequestDto);

        return ResponseEntity.ok(authResponse);
    }
}





