package com.ticketevent.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.model.EventModel;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record EventRequestDto(

        UUID eventId,
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
        LocalDateTime createdAt,
        LocalDateTime updatedAt




) {
    public EventModel createEventRequest() {
        return new EventModel()
                .setEventName(this.eventName())
                .setEventDescription(this.eventDescription())
                .setOrganizerName(this.organizerName())
                .setOrganizerContact(this.organizerContact())
                .setEventPrice(this.eventPrice())
                .setImagePath(this.imagePath())
                .setEventDate(this.eventDate())
                .setEventLocation(this.eventLocation())
                .setEventCategory(this.eventCategory())
                .setTotalCapacity(this.totalCapacity())
                .setStartTime(this.startTime())
                .setEndTime(this.endTime());

    }

    public static void updateEventRequest(EventRequestDto eventRequestDto, EventModel eventModel) {
        BeanUtils.copyProperties(eventRequestDto, eventModel);
        eventModel.setEventId(eventRequestDto.eventId());
        //eventModel.setCreatedAt(eventModel.getCreatedAt() == null ? LocalDateTime.now() : eventModel.getCreatedAt());
        eventModel.setUpdatedAt(LocalDateTime.now());

    }


}
