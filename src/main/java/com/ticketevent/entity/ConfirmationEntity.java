package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.EAGER;
import static java.util.UUID.randomUUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "confirmations")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfirmationEntity extends Auditable{
    private String key;
    @OneToOne(targetEntity = UserEntity.class, fetch = EAGER, optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private UserEntity userEntity;

    public ConfirmationEntity(String password, UserEntity userEntity) {
        this.key = randomUUID().toString();
        this.userEntity = userEntity;
    }

}
