package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketevent.enums.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleEntity extends  Auditable{
    private String name;
    private Authority authorities;

}
