package com.ticketevent.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequestDto(
        @NotBlank(message = "Preencha o Email")
        @NotNull(message = "Deve preencher o Email")
        String email,

        @NotBlank(message = "Preencha o Email")
        @Size(min = 4, message = "A Senha deve ter no minimo 6 caracteres")
        String password)
{
}
