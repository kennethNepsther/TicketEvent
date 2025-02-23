package com.ticketevent.entity.dto.request;

import com.ticketevent.entity.EventEntity;
import jakarta.validation.constraints.*;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequestDto(
        UUID eventId,

        @NotBlank(message = "O nome do evento é obrigatório")
        String eventName,

        @NotBlank(message = "A descrição do evento é obrigatória")
        @Size(min = 5, max = 1000, message = "A descrição deve ter entre 10 e 1000 caracteres")
        String eventDescription,

        @NotNull(message = "A data do evento é obrigatória")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Future(message = "A data do evento deve ser futura")
        String eventDate,

        @NotNull(message = "O preço do evento é obrigatório")
        @DecimalMin(value = "0.0", message = "O preço não pode ser negativo")
        BigDecimal eventPrice,

        @NotBlank(message = "A localização do evento é obrigatória")
        String eventLocation,

        @NotNull(message = "A capacidade total é obrigatória")
        @Min(value = 1, message = "A capacidade total deve ser maior que zero")
        Integer totalCapacity,

        @NotNull(message = "A categoria do evento é obrigatória")
        String eventCategory,

        @NotNull(message = "O horário de início é obrigatório")
        @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Formato de hora inválido (HH:mm)")
        String startTime,

        @NotNull(message = "O número de participantes registrados é obrigatório")
        @Min(value = 0, message = "O número de participantes não pode ser negativo")
        Integer registeredParticipants,

        LocalDateTime createdAt,
        LocalDateTime updatedAt


   /*  UUID organizer,
        String eventName,
        String eventDescription,
        BigDecimal eventPrice,
        String organizerName,
        String organizerContact,
        String imagePath,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate eventDate,
        String eventLocation,
        Integer totalCapacity,
        LocalTime startTime,
        LocalTime endTime,
        EventCategory eventCategory,

        *//* String imageName,
         String imageType,*//*

         //byte[] imageData,*/


) {

    public static void updateEventRequest(EventRequestDto eventRequestDto, EventEntity eventEntity) {
        BeanUtils.copyProperties(eventRequestDto, eventEntity);
        eventEntity.setEventId(eventRequestDto.eventId());
        //eventModel.setCreatedAt(eventModel.getCreatedAt() == null ? LocalDateTime.now() : eventModel.getCreatedAt());
        eventEntity.setUpdatedAt(LocalDateTime.now());

    }


}
