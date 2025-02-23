package com.ticketevent.repository;

import com.ticketevent.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<EventEntity, UUID> {

    //Optional<EventModel> findByImageName(String imageName);

}
