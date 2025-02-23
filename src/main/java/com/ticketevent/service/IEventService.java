package com.ticketevent.service;

import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.request.EventRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IEventService {

    List<EventEntity> findAllEvents();
    EventEntity findEventById(UUID eventId);
    void createEvent(UUID organizerId, EventEntity event, MultipartFile file) throws IOException;
    EventEntity updateEvent(EventEntity eventEntity);
    void deleteEvent(UUID id);
    EventEntity createEventFromDTO(EventRequestDto dto, MultipartFile image, UUID organizerId) throws IOException;
}
