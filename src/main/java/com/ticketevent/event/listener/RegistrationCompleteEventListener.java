package com.ticketevent.event.listener;

import com.ticketevent.event.RegistrationCompleteEvent;
import com.ticketevent.service.IEmailService;
import com.ticketevent.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    final IUserService userService;
    final IEmailService mailService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // 1. Get the user registered from the event
        var user = event.getUser();
        //2. Create a verification token for the user
        String verificationToken =  UUID.randomUUID().toString();
        //3. Save the verification token for the user
        userService.saveUserVerificationToken(user, verificationToken);

        // 4. Build the verification URL and send it to the user's email
        String verificationUrl = event.getApplicationUrl() + "/user/verifyEmail/account?token="+verificationToken;

        // 5. Send the email
      // mailService.sendNewAccountVerificationEmail(user.getFirstName(), user.getEmail(), verificationUrl);

        log.info("Sending verification email to {} with verification URL: {}", user.getEmail(), verificationUrl);


    }


}
