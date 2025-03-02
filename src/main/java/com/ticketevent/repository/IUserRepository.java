package com.ticketevent.repository;

import com.ticketevent.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
    Optional<UserEntity> findUserEntityByUserId(UUID uuid);
    Optional<UserEntity> findUserEntityByPhoneNumber(String phoneNumber);
}
