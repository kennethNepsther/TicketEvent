package com.ticketevent.service;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.entity.dto.request.OrganizerUpdateRequestDto;

import java.util.List;
import java.util.UUID;

public interface IOrganizerService {
    List<UserEntity> findAllOrganizers();
    UserEntity findOrganizerById(UUID organizerId);
    UserEntity createOrganizer(UserEntity organizer);
    UserEntity updateOrganizerData(UUID organizerId, OrganizerUpdateRequestDto updateRequestDto);
    void deleteOrganizer(UUID organizerId);
}
