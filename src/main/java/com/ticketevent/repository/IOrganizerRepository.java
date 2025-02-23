package com.ticketevent.repository;

import com.ticketevent.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IOrganizerRepository extends JpaRepository<UserEntity, UUID> {
}
