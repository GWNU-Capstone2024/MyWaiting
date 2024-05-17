package com.mascari4615.mwaiting.ticket.repository;

import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
