package com.ticketevent.event.listener;

import com.ticketevent.event.UserEvent;
import com.ticketevent.service.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final IEmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event) {
        switch (event.getType()) {
            case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            case RESET_PASSWORD -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            default -> {}
        }

    }

}
