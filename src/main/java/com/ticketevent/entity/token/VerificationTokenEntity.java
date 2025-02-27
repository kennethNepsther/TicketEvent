package com.ticketevent.entity.token;

import com.ticketevent.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_verification_token")
public class VerificationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID tokenId;
    private String token;
    private Date expirationTime;
    private static final int EXPIRATION_TIME = 15;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public VerificationTokenEntity(String token, UserEntity user) {
        this.token = token;
        this.user = user;
        this.expirationTime = this.getTokenExpirationTime();
    }

    public VerificationTokenEntity(String token) {
        super();
        this.token = token;
        this.expirationTime = this.getTokenExpirationTime();
    }

    private Date getTokenExpirationTime() {
        var calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        return  new Date(calendar.getTime().getTime());
    }
}
