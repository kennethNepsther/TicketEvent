package com.ticketevent.entity.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public interface EventDetailsProjection {
    UUID getEventId();
    //String getProvince();
    Boolean getIsActive();
    String getEventName();
    LocalDate getEventDate();
    LocalTime getStartTime();
    String getEventAddress();
    String getOrganizerName();
    BigDecimal getEventPrice();
    Integer getTotalCapacity();
    String getEventDescription();
    Integer getRegisteredParticipants();
    byte[] getImageData();

}
