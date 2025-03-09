package com.ticketevent.service.impl;

import com.ticketevent.entity.EventEntity;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.repository.IEventRepository;
import com.ticketevent.service.IEventService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
    final IEventRepository eventRepository;

    @Override
    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<EventEntity> getEventById(UUID eventId) {
        return eventRepository.findByEventId(eventId);
    }

    @Override
    public EventEntity createEvent(EventEntity eventRequest, MultipartFile image, HttpServletRequest httpRequest) throws IOException {

        var event = new EventEntity();
        event.setEventName(eventRequest.getEventName());
        event.setEventDescription(eventRequest.getEventDescription());
        event.setEventDate(eventRequest.getEventDate());
        event.setEventPrice(eventRequest.getEventPrice());
        event.setEventAddress(eventRequest.getEventAddress());
        event.setTotalCapacity(eventRequest.getTotalCapacity());
        event.setEventCategory(EventCategory.valueOf(eventRequest.getEventCategory().name()));
        event.setEventProvinceLocation(eventRequest.getEventProvinceLocation());
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
