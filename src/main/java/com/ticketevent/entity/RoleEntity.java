package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ticketevent.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_roles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    private ERole name;



}
