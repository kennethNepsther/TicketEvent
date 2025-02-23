package com.ticketevent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ticketevent.domain.RequestContext;
import com.ticketevent.exceptions.exception.BadRequestException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt","updatedAt"}, allowGetters = true)
public abstract class Auditable {
    @Id
    @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
    @Column(name = "id", updatable = false)
    private UUID id;
    private String referenceId = new AlternativeJdkIdGenerator().generateId().toString();
    @NotNull
    private UUID createdBy;
    @NotNull
    private UUID updatedBy;
    @NotNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @CreatedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforePersist() {
        var userId = RequestContext.getUserId();
        if(userId == null) {
            throw new BadRequestException("Cannot persist User, ID must be provided");
        }
        setCreatedBy(userId);
        setUpdatedBy(userId);
        setCreatedAt(now());
        setUpdatedAt(now());
    }

    @PreUpdate
    public void beforeUpdate() {
        var userId = RequestContext.getUserId();
        if(userId == null) {
            throw new BadRequestException("Cannot update User, ID must be provided");
        }
        setUpdatedBy(userId);
        setUpdatedAt(now());
    }



}
