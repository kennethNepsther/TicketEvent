package com.ticketevent.repository;

import com.ticketevent.entity.token.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IVerificationTokenRepository extends JpaRepository<VerificationTokenEntity, UUID> {
    VerificationTokenEntity findByToken(String token);
}
