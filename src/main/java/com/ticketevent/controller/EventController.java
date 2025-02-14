package com.ticketevent.controller;

import com.ticketevent.model.EventModel;
import com.ticketevent.model.dto.request.EventRequestDto;
import com.ticketevent.model.dto.response.EventResponseDto;
import com.ticketevent.service.IEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ticketevent.util.Helper.addIdToCurrentUrlPath;
import static com.ticketevent.util.Helper.stringToUUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/event")
public class EventController {
    final IEventService eventService;


    @GetMapping
    public ResponseEntity<List<EventResponseDto>> findAllEvents() {
        List<EventModel> events = eventService.findAllEvents();
        return ResponseEntity.ok(events.stream().map(EventResponseDto::new).toList());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventModel> findEventById(@PathVariable String eventId) {
        EventModel event = eventService.findEventById(stringToUUID(eventId));
        return ResponseEntity.ok((event));
    }

    @PostMapping
    public ResponseEntity<Object> saveEvent(@Valid @RequestBody EventRequestDto eventRequestDto) {
        EventModel event = eventService.createEvent(eventRequestDto.createEventRequest());
        log.info( "{}  Ticket saved successfully " ,event.getEventName());
        return ResponseEntity.created(addIdToCurrentUrlPath(String.valueOf(event.getEventId())))
                .body("Evento  "+ event.getEventName() +" criado  com  successo");
    }

    @PutMapping
    public ResponseEntity<EventModel> updateEvent(@Valid @RequestBody EventRequestDto eventRequestDto) {
        var eventModel = new EventModel();
        EventRequestDto.updateEventRequest(eventRequestDto,eventModel);
        return ResponseEntity.ok().body(eventService.updateEvent(eventModel));
    }


    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        findEventById(eventId);
        eventService.deleteEvent(stringToUUID(eventId));
        return ResponseEntity.noContent().build();
    }

}
