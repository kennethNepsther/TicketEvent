package com.ticketevent.service;

import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.response.EventDetailsProjection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventService {
    List<EventEntity> getAllEvents();
    Optional<EventEntity> getEventById(UUID eventId);
    List<EventEntity> searchEventsByCategory(String category);
    EventDetailsProjection getEventDetails(UUID eventId);
    List<EventEntity>searchEventsByParams(String eventName, LocalDate eventDate, String province);
    EventEntity createEvent(EventEntity eventEntity, MultipartFile image, HttpServletRequest httpRequest) throws IOException;
}
