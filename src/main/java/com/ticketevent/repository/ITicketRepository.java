package com.ticketevent.repository;

import com.ticketevent.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ITicketRepository extends JpaRepository<TicketEntity, Long> {
    Optional<TicketEntity> findByQrCode(String qrCode);
}
