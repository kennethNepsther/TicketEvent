package com.ticketevent.service.implementation;

import com.ticketevent.enums.EventCategory;
import com.ticketevent.exceptions.exception.BadRequestException;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.request.EventRequestDto;
import com.ticketevent.repository.IEventRepository;
import com.ticketevent.service.IEventService;
import com.ticketevent.service.IOrganizerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
     final IEventRepository  eventRepository;
     final IOrganizerService organizerService;

    @Override
    public List<EventEntity> findAllEvents() {
        if (eventRepository.count() == 0){
            throw new ObjectNotFoundException("Nenhum Evento  encontrado.");
        }
        return eventRepository.findAll();
    }

    @Override
    public EventEntity findEventById(UUID eventId) {
        try {
            Optional<EventEntity> ticket = eventRepository.findById(eventId);
            return ticket.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado  evento com este id " + eventId));
        }catch (IllegalArgumentException e){
            throw  new ObjectNotFoundException("Não foi encontrado  evento com este id " + eventId);
        }

    }

    @Override
    @Transactional
    public void createEvent(UUID organizerId, EventEntity eventEntity, MultipartFile image) throws IOException {

        var organizer = organizerService.findOrganizerById(organizerId);
        eventEntity.setEventId(null);
        eventEntity.setOrganizer(organizer);
        eventEntity.setCreatedAt(LocalDateTime.now());
        eventEntity.setImageData(image.getBytes());
        eventRepository.save(eventEntity);

    }



    @Override
    public EventEntity updateEvent(EventEntity eventEntity) {
        if (eventEntity.getEventId() == null) {
            throw new BadRequestException("deve inserir o eventId");
        }
        findEventById(eventEntity.getEventId());
        return eventRepository.save(eventEntity);
    }

    @Override
    public void deleteEvent(UUID eventId) {
        findEventById(eventId);
        eventRepository.deleteById(eventId);

    }

    @Override
    public EventEntity createEventFromDTO(EventRequestDto dto, MultipartFile image, UUID organizerId) throws IOException {
            validateEventLogic(dto);

          /* *  if (image != null) {
                imageValidationService.validateImage(image);
            }*/

            var event = new EventEntity();
            event.setEventName(dto.eventName());
            event.setEventDescription(dto.eventDescription());
            event.setEventDate(LocalDate.parse(dto.eventDate()));
            event.setEventPrice(dto.eventPrice());
            event.setEventLocation(dto.eventLocation());
            event.setTotalCapacity(dto.totalCapacity());
            event.setEventCategory(EventCategory.valueOf(dto.eventCategory().toUpperCase()));
            event.setStartTime(LocalTime.parse(dto.startTime()));
            event.setRegisteredParticipants(dto.registeredParticipants());

            if (image != null) {
                event.setImageData(image.getBytes());
            }

            return event;

    }


    private void validateEventLogic(EventRequestDto dto) {
        if (dto.registeredParticipants() > dto.totalCapacity()) {
            throw new BadRequestException("O número de participantes registrados não pode exceder a capacidade total");
        }

        LocalDate eventDate = LocalDate.parse(dto.eventDate());
        if (eventDate.isBefore(LocalDate.now())) {
            throw new BadRequestException("A data do evento não pode ser no passado");
        }
    }





/* *   public byte[] downloadImage(String fileName){
        Optional<EventModel> dbImageData = eventRepository.findByImageName(fileName);
        return decompressImage(dbImageData.get().getImageData());
    }*/
}
