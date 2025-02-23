package com.ticketevent.entity.dto.response;

import com.ticketevent.entity.UserEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class OrganizerResponseDto {
    private UUID organizerId;
    private String organizerName;
    private String organizerEmail;
    private String organizerPhone;

    public OrganizerResponseDto(UserEntity userEntity) {
        this.organizerId = userEntity.getOrganizerId();
        this.organizerName = userEntity.getOrganizerName();
        this.organizerEmail = userEntity.getOrganizerEmail();
        this.organizerPhone = userEntity.getOrganizerPhone();
    }
}
