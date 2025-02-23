package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.EAGER;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "credentials")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CredentialEntity extends Auditable{
    private String password;
    @OneToOne(targetEntity = UserEntity.class, fetch = EAGER, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private UserEntity userEntity;

    public CredentialEntity(String password, UserEntity userEntity) {
        this.password = password;
        this.userEntity = userEntity;
    }
}
