package com.ticketevent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ticket")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
public class TicketModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qrCode = UUID.randomUUID().toString();
    private LocalDateTime generatedAt = LocalDateTime.now();
    private LocalDateTime usedAt;
    private Boolean isUsed = false;
    private Boolean isCancelled = false;

    /*@OneToOne
    private Participant participant;

    @ManyToOne
    private Event event;*/
}
