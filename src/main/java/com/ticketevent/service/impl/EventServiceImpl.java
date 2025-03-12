package com.ticketevent.service.impl;

import com.ticketevent.auth.service.IAuthService;
import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.response.EventDetailsProjection;
import com.ticketevent.enums.EProvinces;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.repository.IEventRepository;
import com.ticketevent.service.IEventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ticketevent.constant.Constants.EVENT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
    final IEventRepository eventRepository;
    final IAuthService authService;

    @Override
    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<EventEntity> getEventById(UUID eventId) {
        return eventRepository.findByEventId(eventId);
    }



    @Override
    public List<EventEntity> searchEventsByCategory(String category) {
        try {
            return eventRepository.findByEveCategory(EventCategory.valueOf(category));
        }catch (IllegalArgumentException e) {
            throw  new ObjectNotFoundException(EVENT_NOT_FOUND_MESSAGE);
        }

    }

    @Override
    public EventDetailsProjection getEventDetails(UUID eventId) {
        return eventRepository.findEventDetailsById(eventId);
    }


    @Override
    public List<EventEntity> searchEventsByParams(String eventName, LocalDate eventDate, String province) {
        // Normalize input parameters
        String normalizedName = eventName != null && !eventName.trim().isEmpty() ? eventName.trim() : null;
        LocalDate normalizedDate = eventDate!= null? eventDate : LocalDate.now();
        EProvinces normalizedProvince = province!= null ? EProvinces.valueOf(province.toUpperCase()) : null;

        return eventRepository.findByParams(normalizedName, eventDate, normalizedProvince);
    }

    @Override
    public EventEntity createEvent(EventEntity eventRequest, MultipartFile image, HttpServletRequest httpRequest) throws IOException {

        var event = new EventEntity();
        event.setOrganizer(eventRequest.getOrganizer());
        event.setEventName(eventRequest.getEventName());
        event.setEventDescription(eventRequest.getEventDescription());
        event.setEventDate(eventRequest.getEventDate());
        event.setEventPrice(eventRequest.getEventPrice());
        event.setEventAddress(eventRequest.getEventAddress());
        event.setTotalCapacity(eventRequest.getTotalCapacity());
        event.setEventCategory(EventCategory.valueOf(eventRequest.getEventCategory().name()));
        event.setProvince(EProvinces.valueOf(eventRequest.getProvince().name()));
        event.setStartTime(eventRequest.getStartTime());
        event.setRegisteredParticipants(eventRequest.getRegisteredParticipants());
        event.setImageData(image.getBytes());

      /*  if (image != null && !image.isEmpty()) {
            event.setImageData(image.getBytes());
        }*/

       return eventRepository.save(event);

        // TODO: Send  email  notification
        // SendEmailService.sendEmail();
        // TODO: Send  sms  notification
        // SmsService.sendSms();

    }


}
