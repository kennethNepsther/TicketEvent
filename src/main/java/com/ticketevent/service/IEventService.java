package com.ticketevent.service;

import com.ticketevent.entity.EventEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventService {
    List<EventEntity> getAllEvents();
    Optional<EventEntity> getEventById(UUID eventId);
    EventEntity createEvent(EventEntity eventEntity, MultipartFile image, HttpServletRequest httpRequest) throws IOException;
}
