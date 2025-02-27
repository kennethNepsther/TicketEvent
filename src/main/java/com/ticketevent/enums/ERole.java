package com.ticketevent.enums;

import lombok.Getter;

@Getter
public enum ERole {
    ADMIN("admin"),
    PARTICIPANT("participant" ),
    ORGANIZER("organizer");

    private final String role;

    ERole(String role) {
        this.role = role;
    }


}
