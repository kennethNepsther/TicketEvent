package com.ticketevent.repository;

import com.ticketevent.entity.EventEntity;
import com.ticketevent.entity.dto.response.EventDetailsProjection;
import com.ticketevent.enums.EProvinces;
import com.ticketevent.enums.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<EventEntity, UUID> {
    Optional<EventEntity> findByEventId(UUID eventId);

    @Query("SELECT e FROM EventEntity e WHERE " +
            "(:eventName IS NULL OR LOWER(e.eventName) LIKE LOWER(CONCAT('%', :eventName, '%'))) AND " +
            "(:province IS NULL OR e.province = :province) AND " +
            "(:eventDate IS NULL OR e.eventDate = :eventDate)")
    List<EventEntity> findByParams(
            @Param("eventName") String eventName,
            @Param("eventDate") LocalDate eventDate,
            @Param("province") EProvinces province);


    @Query("SELECT ev FROM EventEntity ev WHERE " +
            "(:category IS NULL OR ev.eventCategory = :category)")
    List<EventEntity> findByEveCategory(@Param("category") EventCategory category);


    @Query("SELECT e.eventId as eventId," +
            " e.isActive as isActive," +
            " e.ImageData as imageData," +
            " e.eventName as eventName," +
            " e.eventDate as eventDate," +
            " e.startTime as startTime," +
            " e.eventPrice as eventPrice," +
            " e.eventAddress as eventAddress," +
            " e.totalCapacity as totalCapacity," +
            " e.eventDescription as eventDescription," +
            " e.registeredParticipants as registeredParticipants," +
            " u.firstName as organizerName " +
            "FROM EventEntity e " +
            "JOIN e.organizer u " +
            "WHERE e.eventId = :eventId")
    EventDetailsProjection findEventDetailsById(UUID eventId);
}
