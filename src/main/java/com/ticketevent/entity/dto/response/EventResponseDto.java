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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate eventDate;
    private LocalTime startTime;
    private String eventAddress;
    private BigDecimal eventPrice;
    //private Integer totalCapacity;
    private String eventDescription;
    private EventCategory eventCategory;
    private String eventProvinceLocation;
   // private Integer registeredParticipants;
    private Boolean isActive;
    private  byte[] imagePath;


    public EventResponseDto(EventEntity eventEntity) {
        this.eventId = eventEntity.getEventId();
        this.eventName = eventEntity.getEventName();
        this.eventDate = eventEntity.getEventDate();
        this.startTime = eventEntity.getStartTime();
        this.eventPrice = eventEntity.getEventPrice();
        this.eventAddress = eventEntity.getEventAddress();
       // this.totalCapacity = eventEntity.getTotalCapacity();
        this.eventCategory = eventEntity.getEventCategory();
        this.eventDescription = eventEntity.getEventDescription();
        this.eventProvinceLocation = eventEntity.getEventProvinceLocation();
       // this.registeredParticipants = eventEntity.getRegisteredParticipants();
        this.isActive = eventEntity.getIsActive();
        this.imagePath = eventEntity.getImageData();

    }
}
