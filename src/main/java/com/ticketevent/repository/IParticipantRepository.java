package com.ticketevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipantRepository extends JpaRepository<ParticipantModel, Long> {
}
