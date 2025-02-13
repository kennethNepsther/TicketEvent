package com.ticketevent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_participant")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class ParticipantModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

   /* @ManyToOne
    private Event event;*/
}
