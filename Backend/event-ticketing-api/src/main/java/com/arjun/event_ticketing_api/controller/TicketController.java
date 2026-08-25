package com.arjun.event_ticketing_api.controller;

import com.arjun.event_ticketing_api.model.Ticket;
import com.arjun.event_ticketing_api.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public Ticket bookTicket(@Valid @RequestBody Ticket ticket,
                             Principal principal) {

        ticket.setCustomerName(principal.getName());

        return ticketService.bookTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getMyBookings(Principal principal) {

        String username = principal.getName();

        return ticketService.getMyBookings(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(
            @PathVariable Long id,
            Principal principal) {

        try {

            String username = principal.getName();

            ticketService.deleteTicket(id, username);

            return ResponseEntity.ok("Booking deleted successfully");

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
}