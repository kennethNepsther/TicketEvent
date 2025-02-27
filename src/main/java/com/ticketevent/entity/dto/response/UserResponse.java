package com.ticketevent.entity.dto.response;

import com.ticketevent.entity.UserEntity;
import lombok.Data;

import java.util.UUID;
@Data
public class UserResponse {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
   /* * private boolean isEnabled ;
    private boolean isAccountNonLocked;*/

    public UserResponse(UserEntity user) {
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        // *this.isEnabled = user.isEnabled();
        //this.isAccountNonLocked = user.isAccountNonLocked();

    }
}
