package com.ticketevent.controller;

import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.response.EventResponseDto;
import com.ticketevent.enums.EventCategory;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.service.IEventService;
import com.ticketevent.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.ticketevent.constant.Constants.*;
import static com.ticketevent.util.Helper.stringToUUID;
import static com.ticketevent.util.UrlUtils.addIdToCurrentUrlPath;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Eventos")
@RequestMapping("/event")
@CrossOrigin(origins = "*")
public class EventController {

    final IUserService userService;
    final IEventService eventService;



    @GetMapping("/all")
    public ResponseEntity<List<EventResponseDto>> findAll() {
        List<EventEntity> events = eventService.getAllEvents();
        return ResponseEntity.ok(events.stream().map(EventResponseDto::new).toList());


    }
    @GetMapping("/find/{eventId}")
    public ResponseEntity<EventEntity> findById(@PathVariable String eventId) {
        Optional<EventEntity> event = eventService.getEventById(stringToUUID(eventId));
        return ResponseEntity.ok((event).orElseThrow(() -> new ObjectNotFoundException(EVENT_NOT_FOUND_MESSAGE)));
    }


    @PostMapping(value = "/save", consumes = {"multipart/form-data"})
    public ResponseEntity<Object> createEvent(@RequestParam("eventName") String eventName,
                                              @RequestParam("eventDescription") String eventDescription,
                                              @RequestParam("eventDate") String eventDate,
                                              @RequestParam("eventPrice") Double eventPrice,
                                              @RequestParam("eventAddress") String eventAddress,
                                              @RequestParam("eventProvinceLocation") String eventProvinceLocation,
                                              @RequestParam("totalCapacity") Integer totalCapacity,
                                              @RequestParam("eventCategory") String eventCategory,
                                              @RequestParam("startTime") String startTime,
                                              @RequestParam(value = "image", required = false) MultipartFile image,
                                              JwtAuthenticationToken token,
                                              final HttpServletRequest httpRequest) throws IOException {


        //var user = userService.getUserById(UUID.fromString(token.getName()));


        var event = new EventEntity();
        event.setEventName(eventName);
        event.setEventDescription(eventDescription);
        event.setEventDate(LocalDate.parse(eventDate));
        event.setEventPrice(BigDecimal.valueOf(eventPrice));
        event.setEventAddress(eventAddress);
        event.setEventProvinceLocation(eventProvinceLocation);
        event.setTotalCapacity(totalCapacity);
        event.setEventCategory(EventCategory.valueOf(eventCategory));
        event.setStartTime(LocalTime.parse(startTime));
        event.setImageData(image.getBytes());

        try {
            var newEvent = eventService.createEvent(event, image, httpRequest);
            URI uri = addIdToCurrentUrlPath(newEvent.getEventId().toString());

           return ResponseEntity.created(uri).body(EVENT_SAVE_SUCCESSFULLY_MESSAGE);
        } catch (DataIntegrityViolationException | IOException e) {
            log.error(ERROR_ON_CREATE_EVENT, e);
            throw new DataIntegrityViolationException(ERROR_ON_CREATE_EVENT);

        }
    }

}
