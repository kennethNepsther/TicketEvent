package com.ticketevent.controller;

import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;
import com.ticketevent.exceptions.exception.BadCredentialException;
import com.ticketevent.repository.IUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ticketevent.constant.Constants.WRONG_CREDENTIALS_MESSAGE;
import static java.time.Instant.now;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class TokenController {
     final JwtEncoder jwtEncoder;
    final IUserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;
    final


    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody  AuthRequestDto authRequestDto) {
        var user = userRepository.findByEmailIgnoreCase(authRequestDto.email());

        if(user.isEmpty() || !user.get().isLoginCorrect(authRequestDto, passwordEncoder)){
            throw new BadCredentialException(WRONG_CREDENTIALS_MESSAGE);
        }
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("ticket-event")
                .subject(user.get().getUserId().toString())
                .expiresAt(now().plusSeconds(expiresIn))
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new AuthResponseDto(token,expiresIn));



    }
}
