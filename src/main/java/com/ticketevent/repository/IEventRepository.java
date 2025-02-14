package com.ticketevent.repository;

import com.ticketevent.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<EventModel, UUID> {
}
