package com.ticketevent.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ticketevent.service.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketService {
    //private final ITicketRepository ticketRepository;
    //private final IEventRepository eventRepository;


 /*   @Override
    public TicketEntity generateTicket(UUID eventId, ParticipantModel participant) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getRegisteredParticipants() >= event.getTotalCapacity()) {
            throw new RuntimeException("Event is full");
        }

       *//* participant = participantRepository.save(participant);

        var ticket = new TicketModel();
        ticket.setEventModel(event);
        ticket.setParticipant(participant);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);
        eventRepository.save(event);

        return ticketRepository.save(ticket);*//*

        return  null;

    }*/

    public byte[] generateQRCode(String qrCode) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            MatrixToImageWriter.writeToStream(
                    qrCodeWriter.encode(qrCode, BarcodeFormat.QR_CODE, 350, 350),
                    "PNG",
                    outputStream
            );
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR Code", e);
        }
    }

   /* public boolean validateTicket(String qrCode) {
        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid ticket"));

        if (ticket.getIsCancelled()) {
            throw new RuntimeException("Ticket is cancelled");
        }

        if (ticket.getIsUsed()) {
            throw new RuntimeException("Ticket already used");
        }

        ticket.setIsUsed(true);
        ticket.setUsedAt(LocalDateTime.now());
        ticketRepository.save(ticket);

        return true;
    }*/

   /* public void cancelTicket(String qrCode) {
        Ticket ticket = ticketRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid ticket"));

        ticket.setIsCancelled(true);
        ticketRepository.save(ticket);

        Event event = ticket.getEvent();
        event.setRegisteredParticipants(event.getRegisteredParticipants() - 1);
        eventRepository.save(event);
    }*/
}


