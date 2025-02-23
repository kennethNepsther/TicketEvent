package com.ticketevent.service;

import com.ticketevent.entity.TicketEntity;

import java.util.UUID;

public interface ITicketService {
    TicketEntity generateTicket(UUID eventId, ParticipantModel participant);
}
