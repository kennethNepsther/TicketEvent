package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketevent.enums.EventCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_event")
@Accessors(chain = true)
@EqualsAndHashCode(of = "eventId")
public class EventEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID eventId;
    private String eventName;
    private String eventDescription;
    private String eventAddress;
    private String eventProvinceLocation;
    private BigDecimal eventPrice = BigDecimal.ZERO;
    private Integer totalCapacity;
    private Integer registeredParticipants = 0;
    private Boolean isActive = true;
    @Enumerated(EnumType.STRING)
    private EventCategory eventCategory;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate eventDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
   /* @Lob
    @Column(name = "image_path")
    private byte[] imagePath;*/
   @Lob
    @Column(name = "image_data" ,length = 10000000)
    private byte[] ImageData;


    @CreatedDate
    private Instant createdAt;
    @CreatedDate
    private Instant updatedAt;

    /*@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "organizerId", nullable = false)
    private UserEntity organizer;*/



}
