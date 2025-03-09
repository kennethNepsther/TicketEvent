package com.ticketevent.repository;

import com.ticketevent.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface IEventRepository extends JpaRepository<EventEntity, UUID> {
   Optional<EventEntity> findByEventId(UUID eventId);
  /* Optional<EventEntity> findByTitle(String title);
   Optional<EventEntity> findByDescription(String description);
   Optional<EventEntity> findByDate(String date);
   Optional<EventEntity> findByLocation(String location);*/

}
