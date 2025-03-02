package com.ticketevent.auth;

import com.ticketevent.entity.UserEntity;
import com.ticketevent.enums.ERole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Getter
@Setter
public class UserModelDetails implements UserDetails {

    private String username;
    private String password;
    private String phoneNumber;
    private boolean isEnabled;
    private ERole roles;



    public UserModelDetails(UserEntity userEntity) {
        this.username = userEntity.getEmail();
        this.password = userEntity.getPassword();
        this.phoneNumber = userEntity.getPhoneNumber();
        this.isEnabled = userEntity.isEnabled();

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles == ERole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_PARTICIPANT"),
                    new SimpleGrantedAuthority("ROLE_ORGANIZER"));
        }
        if (this.roles == ERole.USER){
            return List.of(new SimpleGrantedAuthority("ROLE_ORGANIZER"),
                    new SimpleGrantedAuthority("ROLE_PARTICIPANT"));

        }else{
            return List.of(new SimpleGrantedAuthority("ROLE_PARTICIPANT"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
