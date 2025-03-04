package com.ticketevent.repository;

import com.ticketevent.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
    Optional<UserEntity> findUserEntityByUserId(UUID uuid);
    Optional<UserEntity> findUserEntityByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = 'ADMIN'")
    List<UserEntity> findAllAdmins();


    @Query("SELECT u FROM UserEntity u JOIN u.roles r WHERE r.name = 'USER'")
    List<UserEntity> findAllUsers();





}
