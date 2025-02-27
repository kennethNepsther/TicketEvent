package com.ticketevent.util;

import com.ticketevent.entity.RoleEntity;
import com.ticketevent.entity.UserEntity;

import java.util.UUID;

public class UserUtils {
    public static UserEntity createUserEntity(String firstName, String lastName, String phoneNumber, String email, RoleEntity role) {
        return UserEntity.builder()
                .userId(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .email(email)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .build();

    }


}
