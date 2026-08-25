package com.arjun.event_ticketing_api.service;

import com.arjun.event_ticketing_api.model.Event;
import com.arjun.event_ticketing_api.model.Ticket;
import com.arjun.event_ticketing_api.repository.EventRepository;
import com.arjun.event_ticketing_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;


    // 1. Book a ticket
    public Ticket bookTicket(Ticket ticket) {

        Optional<Event> optionalEvent =
                eventRepository.findById(ticket.getEventId());

        if (optionalEvent.isEmpty()) {
            throw new RuntimeException("Event not found");
        }

        Event event = optionalEvent.get();

        // Check available seats
        if (ticket.getSeatsBooked() > event.getAvailableSeats()) {
            throw new RuntimeException("Not enough seats available");
        }

        // Reduce available seats
        event.setAvailableSeats(
                event.getAvailableSeats() - ticket.getSeatsBooked()
        );

        // Calculate total price
        long totalPrice =
                event.getTicketPrice() * ticket.getSeatsBooked();

        ticket.setTotalPrice(totalPrice);

        // Save updated event
        eventRepository.save(event);

        // Save ticket
        ticketRepository.save(ticket);

        return ticket;
    }


    // 2. Get bookings of the logged-in user
    public List<Ticket> getMyBookings(String username) {

        return ticketRepository.findByCustomerName(username);
    }


    // 3. Delete/cancel a ticket
    public void deleteTicket(Long ticketId, String username) {

        Optional<Ticket> optionalTicket =
                ticketRepository.findById(ticketId);

        if (optionalTicket.isEmpty()) {
            throw new RuntimeException("Ticket not found");
        }

        Ticket ticket = optionalTicket.get();

        // Make sure this ticket belongs to the logged-in user
        if (!ticket.getCustomerName().equals(username)) {
            throw new RuntimeException(
                    "You are not allowed to delete this ticket"
            );
        }

        // Find the event
        Optional<Event> optionalEvent =
                eventRepository.findById(ticket.getEventId());

        if (optionalEvent.isPresent()) {

            Event event = optionalEvent.get();

            // Return booked seats to available seats
            event.setAvailableSeats(
                    event.getAvailableSeats() + ticket.getSeatsBooked()
            );

            eventRepository.save(event);
        }

        // Delete ticket
        ticketRepository.delete(ticket);
    }


    // 4. Get all tickets - Admin
    public List<Ticket> getAllTickets() {

        return ticketRepository.findAll();
    }
}