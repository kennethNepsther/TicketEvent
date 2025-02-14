package com.ticketevent.service;

import com.ticketevent.model.ParticipantModel;
import com.ticketevent.model.TicketModel;

import java.util.UUID;

public interface ITicketService {
    TicketModel generateTicket(UUID eventId, ParticipantModel participant);
}
