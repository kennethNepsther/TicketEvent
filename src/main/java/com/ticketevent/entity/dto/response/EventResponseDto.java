package com.ticketevent.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.entity.EventEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class EventResponseDto {
    private UUID eventId;
    private String eventName;
    private String imagePath;
    private String eventDescription;
    private String eventLocation;
    private String organizerName;
    private String organizerContact;
    private BigDecimal eventPrice;
    private Integer totalCapacity;
    private Integer registeredParticipants;
    private Boolean isActive;
    private EventCategory eventCategory;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate eventDate;
    private LocalTime startTime;
    private LocalTime endTime;


    public EventResponseDto(EventEntity eventEntity) {
        this.eventId = eventEntity.getEventId();
        this.eventName = eventEntity.getEventName();
        this.eventDescription = eventEntity.getEventDescription();
        this.eventLocation = eventEntity.getEventLocation();
        this.imagePath = eventEntity.getImagePath();
        this.eventPrice = eventEntity.getEventPrice();
        this.totalCapacity = eventEntity.getTotalCapacity();
        this.eventCategory = eventEntity.getEventCategory();
        this.registeredParticipants = eventEntity.getRegisteredParticipants();
        this.isActive = eventEntity.getIsActive();
        this.eventDate = eventEntity.getEventDate();
        this.startTime = eventEntity.getStartTime();

    }
}
