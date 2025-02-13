package com.ticketevent.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.model.EventModel;
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


    public EventResponseDto(EventModel eventModel) {
        this.eventId = eventModel.getEventId();
        this.eventName = eventModel.getEventName();
        this.eventDescription = eventModel.getEventDescription();
        this.eventLocation = eventModel.getEventLocation();
        this.organizerName = eventModel.getOrganizerName();
        this.organizerContact = eventModel.getOrganizerContact();
        this.imagePath = eventModel.getImagePath();
        this.eventPrice = eventModel.getEventPrice();
        this.totalCapacity = eventModel.getTotalCapacity();
        this.eventCategory = eventModel.getEventCategory();
        this.registeredParticipants = eventModel.getRegisteredParticipants();
        this.isActive = eventModel.getIsActive();
        this.eventDate = eventModel.getEventDate();
        this.startTime = eventModel.getStartTime();
        this.endTime = eventModel.getEndTime();
    }
}
