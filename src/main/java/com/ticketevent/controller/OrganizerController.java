package com.ticketevent.controller;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.request.OrganizerRequestDto;
import com.ticketevent.entity.dto.request.OrganizerUpdateRequestDto;
import com.ticketevent.entity.dto.response.OrganizerResponseDto;
import com.ticketevent.service.IOrganizerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ticketevent.util.Helper.addIdToCurrentUrlPath;
import static com.ticketevent.util.Helper.stringToUUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/organizer")
public class OrganizerController {
    final IOrganizerService organizerService;


    @GetMapping
    public ResponseEntity<List<OrganizerResponseDto>> getAllOrganizer() {
        List<UserEntity> organizers= organizerService.findAllOrganizers();
        return ResponseEntity.ok(organizers.stream().map(OrganizerResponseDto::new).toList());
    }

    @GetMapping("/{organizerId}")
    public ResponseEntity<UserEntity> getOrganizerById(@PathVariable String organizerId) {
        UserEntity organizer = organizerService.findOrganizerById(stringToUUID(organizerId));
        return ResponseEntity.ok((organizer));
    }

    @PostMapping
    public ResponseEntity<Object> saveEvent(@Valid @RequestBody OrganizerRequestDto organizerRequestDto) {
        var organizer = new UserEntity();
        BeanUtils.copyProperties(organizerRequestDto, organizer);
        UserEntity newOrganizer = organizerService.createOrganizer(organizer);
        return ResponseEntity.created(addIdToCurrentUrlPath(String.valueOf(newOrganizer.getOrganizerId())))
                .body("Evento  "+ newOrganizer.getOrganizerName() +" criado  com  successo");   }



    @PutMapping("/{organizerId}")
    public ResponseEntity<UserEntity> updateEvent(@PathVariable String organizerId, @Valid  @RequestBody OrganizerUpdateRequestDto updateRequestDto) {
        return ResponseEntity.ok().body(organizerService.updateOrganizerData(stringToUUID(organizerId), updateRequestDto));
    }


    @DeleteMapping("/{organizerId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String organizerId) {
        getOrganizerById(organizerId);
        organizerService.deleteOrganizer(stringToUUID(organizerId));
        return ResponseEntity.noContent().build();
    }

}
