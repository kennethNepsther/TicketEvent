package com.ticketevent.repository;

import com.ticketevent.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ITicketRepository extends JpaRepository<TicketModel, Long> {
    Optional<TicketModel> findByQrCode(String qrCode);
}
