package com.ticketevent.service.implementation;

import com.ticketevent.exceptions.exception.BadRequestException;
import com.ticketevent.exceptions.exception.ObjectNotFoundException;
import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.request.OrganizerUpdateRequestDto;
import com.ticketevent.repository.IOrganizerRepository;
import com.ticketevent.service.IOrganizerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@AllArgsConstructor
public class OrganizerServiceImpl implements IOrganizerService {
    final IOrganizerRepository organizerRepository;
    @Override
    public List<UserEntity> findAllOrganizers() {
        if (organizerRepository.count() == 0){
            throw new ObjectNotFoundException("Nenhum organizador  encontrado.");
        }
        return organizerRepository.findAll();
    }

    @Override
    public UserEntity findOrganizerById(UUID organizerId) {
        try {
            Optional<UserEntity> organizer = organizerRepository.findById(organizerId);
            return organizer.orElseThrow(() -> new ObjectNotFoundException("Não foi encontrado  organizador com este id " + organizerId));
        }catch (IllegalArgumentException e){
            throw  new BadRequestException("Não foi encontrado  organizador com este id " + organizerId);
        }
    }

    @Override
    public UserEntity createOrganizer(UserEntity organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public UserEntity updateOrganizerData(UUID organizerId, OrganizerUpdateRequestDto updateRequestDto) {
        var newOrganizer = findOrganizerById(organizerId);
        newOrganizer.setOrganizerId(organizerId);
        newOrganizer.setOrganizerName(updateRequestDto.organizerName());
        newOrganizer.setOrganizerEmail(updateRequestDto.organizerEmail());
        newOrganizer.setOrganizerPhone(updateRequestDto.organizerPhone());

        return organizerRepository.save(newOrganizer);
    }

    @Override
    public void deleteOrganizer(UUID organizerId) {
        findOrganizerById(organizerId);
        organizerRepository.deleteById(organizerId);

    }
}
