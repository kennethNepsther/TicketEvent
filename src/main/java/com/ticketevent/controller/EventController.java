package com.ticketevent.controller;

import com.ticketevent.enums.EventCategory;
import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.request.EventRequestDto;
import com.ticketevent.entity.dto.response.EventResponseDto;
import com.ticketevent.service.IEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

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
        List<EventEntity> events = eventService.findAllEvents();
        return ResponseEntity.ok(events.stream().map(EventResponseDto::new).toList());
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventEntity> findEventById(@PathVariable String eventId) {
        EventEntity event = eventService.findEventById(stringToUUID(eventId));
        return ResponseEntity.ok((event));
    }

    @PostMapping
    public ResponseEntity<Object> saveEvent(@RequestParam("organizer") String organizer,
                                            @RequestParam("eventName") String eventName,
                                            @RequestParam("eventDescription") String eventDescription,
                                            @RequestParam("eventDate") String eventDate,
                                            @RequestParam("eventPrice") Double eventPrice,
                                            @RequestParam("eventLocation") String eventLocation,
                                            @RequestParam("totalCapacity") Integer totalCapacity,
                                            @RequestParam("eventCategory") String eventCategory,
                                            @RequestParam("startTime") String startTime,
                                            @RequestParam("registeredParticipants") Integer registeredParticipants,
                                            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {


        var event = new EventEntity();
        event.setEventName(eventName);
        event.setEventDescription(eventDescription);
        event.setEventDate(LocalDate.parse(eventDate));
        event.setEventPrice(BigDecimal.valueOf(eventPrice));
        event.setEventLocation(eventLocation);
        event.setTotalCapacity(totalCapacity);
        event.setEventCategory(EventCategory.valueOf(eventCategory));
        event.setStartTime(LocalTime.parse(startTime));
        event.setRegisteredParticipants(registeredParticipants);
        event.setImageData(image.getBytes());

        eventService.createEvent(UUID.fromString(organizer),event,image);
        log.info( "{}  Ticket saved successfully " ,event.getEventName());
        return ResponseEntity.created(addIdToCurrentUrlPath(String.valueOf(event.getEventId())))
                .body("Evento  "+ event.getEventName() +" criado  com  successo");
    }


    @PutMapping
    public ResponseEntity<EventEntity> updateEvent(@Valid @RequestBody EventRequestDto eventRequestDto) {
        var eventModel = new EventEntity();
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
