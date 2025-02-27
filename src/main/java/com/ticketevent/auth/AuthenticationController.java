package com.ticketevent.auth;

import com.ticketevent.entity.dto.request.AuthRequestDto;
import com.ticketevent.entity.dto.response.AuthResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "Authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {

  /*  final AdAuthService authService;
    private AuthenticationManager authenticationManager;
    final TokenConfigurationService tokenConfigurationService;*/

    @PostMapping
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody AuthRequestDto request) {
        String securityKey = "3174fc180d40530ef6e138828aefce929f3cd58a";

      /*  authService.requestExecutionNetwork(request.username(), request.password());
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), securityKey);

            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenConfigurationService.generateToken((UserModel) auth.getPrincipal());
            return ResponseEntity.ok(new AuthResponseDto(token));

        }catch (AuthenticationException exception){
            throw new CredentialInvalidException("Utilizador ou senha inválida");
        }

            */
        return null;
    }
}
