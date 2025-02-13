package com.ticketevent.service;

import com.ticketevent.model.EventModel;

import java.util.List;
import java.util.UUID;

public interface IEventService {

    List<EventModel> findAllEvents();
    EventModel findEventById(UUID eventId);
    EventModel createEvent(EventModel event);
    EventModel updateEvent(EventModel eventModel);
    void deleteEvent(UUID id);
}
