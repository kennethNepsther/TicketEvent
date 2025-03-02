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
public class RoleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    private String name;
    //@Enumerated(EnumType.STRING)
   // private ERole name;



}
