package com.ticketevent.entity.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrganizerRequestDto(
        UUID id,
        @NotNull(message = "O nome  é obrigatório")   String organizerName,

        @NotNull(message = "O organizerEmail  é obrigatório")   String organizerEmail,

        @NotNull(message = "O telefone  é obrigatório") String organizerPhone,

        @NotNull(message = "A senha  é obrigatória") String password

) {


}
