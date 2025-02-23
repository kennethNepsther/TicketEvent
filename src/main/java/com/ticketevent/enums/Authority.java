package com.ticketevent.enums;

import lombok.Getter;

import static com.ticketevent.constant.Constants.*;

@Getter
public enum Authority {

    ADMIN(ADMIN_AUTHORITY),
    PARTICIPANT(PARTICIPANT_AUTHORITY),
    ORGANIZER(ORGANIZER_AUTHORITY);

    private final String   value;

    Authority(String value) {
        this.value = value;
    }

}
