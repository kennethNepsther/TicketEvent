package com.ticketevent.service.implementation;

import com.ticketevent.exceptions.exception.BadRequestException;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.model.EventModel;
import com.ticketevent.repository.IEventRepository;
import com.ticketevent.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
     final IEventRepository  eventRepository;

    @Override
    public List<EventModel> findAllEvents() {
        if (eventRepository.count() == 0){
            throw new ObjectNotFoundException("Nenhum Evento  encontrado.");
        }
        return eventRepository.findAll();
    }

    @Override
    public EventModel findEventById(UUID eventId) {
        try {
            Optional<EventModel> ticket = eventRepository.findById(eventId);
            return ticket.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado  evento com este id " + eventId));
        }catch (IllegalArgumentException e){
            throw  new ObjectNotFoundException("Não foi encontrado  evento com este ticketId " + eventId);
        }

    }

    @Override
    public EventModel createEvent(EventModel eventModel) {
        eventModel.setEventId(null);
        eventModel.setCreatedAt(LocalDateTime.now());
        return eventRepository.save(eventModel);
    }

    @Override
    public EventModel updateEvent(EventModel eventModel) {
        if (eventModel.getEventId() == null) {
            throw new BadRequestException("deve inserir o eventId");
        }
        findEventById(eventModel.getEventId());
        return eventRepository.save(eventModel);
    }

    @Override
    public void deleteEvent(UUID eventId) {
        findEventById(eventId);
        eventRepository.deleteById(eventId);

    }
}
