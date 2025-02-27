package com.ticketevent.entity.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public record UserCreateRequestDto(
        //UUID id,
        @NotBlank(message = "Preencha o primeiro nome")
        @NotNull(message = "O primeiro nome  é obrigatório")
        @Size(min = 4, max = 30, message = "O nome deve ter entre 4 e 10 caracteres")
        String firstName,

        @NotBlank(message = "Preencha o Ultimo nome")
        @NotNull(message = "Ultimo nome  é obrigatório")
        @Size(min = 4, max = 30, message = "O nome deve ter entre 4 e 10 caracteres")
        String lastName,

        @NotBlank(message = "Preencha o Email")
        @NotNull(message = "O Email  é obrigatório")
        @Email(message = "formato do email inválido", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        String email,


        @NotNull(message = "O telefone  é obrigatório")
        @Size(min = 9, max = 9, message = "O telefone deve ter entre 9 caracteres")
        String phoneNumber,

        @NotBlank(message = "Preencha a senha")
        @NotNull(message = "A senha  é obrigatório")
        @Size(min = 8, message = "A senha deve ter entre 8 caracteres")
        String password,

        Set<String> roles

) {


}
