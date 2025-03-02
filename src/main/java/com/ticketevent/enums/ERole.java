package com.ticketevent.enums;

import lombok.Getter;

@Getter
public enum ERole {
    ADMIN(1L),
    USER(2L);

   final long roleId;

    ERole(long roleId) {
        this.roleId = roleId;
    }


}
