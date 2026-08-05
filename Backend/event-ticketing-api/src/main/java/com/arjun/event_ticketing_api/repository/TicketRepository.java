package com.arjun.event_ticketing_api.repository;


import com.arjun.event_ticketing_api.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository  extends JpaRepository<Ticket,Long> {
}
