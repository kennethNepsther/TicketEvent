package com.ticketevent.service.impl;

import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;
import com.ticketevent.exceptions.exception.BadCredentialException;
import com.ticketevent.repository.IUserRepository;
import com.ticketevent.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import static com.ticketevent.constant.Constants.USER_NOT_VERIFIED_MESSAGE;
import static com.ticketevent.constant.Constants.WRONG_CREDENTIALS_MESSAGE;
import static java.time.Instant.now;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    final JwtEncoder jwtEncoder;
    final IUserRepository userRepository;
    final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {

        var user = userRepository.findByEmailIgnoreCase(authRequestDto.email());

        if(user.isEmpty() || !user.get().isLoginCorrect(authRequestDto, passwordEncoder)){
            throw new BadCredentialException(WRONG_CREDENTIALS_MESSAGE);
        }
        if(!user.get().isEnabled() ||!user.get().isAccountNonLocked()){
            throw new BadCredentialException(USER_NOT_VERIFIED_MESSAGE);
        }
        var expiresIn = 300L;
        var claims = JwtClaimsSet.builder()
                .issuer("ticket-event")
                .subject(user.get().getUserId().toString())
                .expiresAt(now().plusSeconds(expiresIn))
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
         return new AuthResponseDto(token, expiresIn);
    }
}
