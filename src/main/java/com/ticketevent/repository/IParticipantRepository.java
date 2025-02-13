package com.ticketevent.repository;

import com.ticketevent.model.ParticipantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParticipantRepository extends JpaRepository<ParticipantModel, Long> {
}
