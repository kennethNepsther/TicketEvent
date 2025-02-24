package com.ticketevent.event;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {
    private EventType type;
    private UserEntity user;
    private Map<?,?>data;
}
