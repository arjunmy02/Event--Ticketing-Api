package com.arjun.event_ticketing_api.service;
import java.util.List;
import java.util.Optional;

import com.arjun.event_ticketing_api.model.Event;
import com.arjun.event_ticketing_api.model.Ticket;
import com.arjun.event_ticketing_api.repository.EventRepository;
import com.arjun.event_ticketing_api.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    public Ticket bookTicket(Ticket ticket){

        Optional<Event> optionalEvent =
                eventRepository.findById(ticket.getEventId());

        if(optionalEvent.isEmpty()){
            throw new RuntimeException("Event not found");
        }

        Event event = optionalEvent.get();

        if (ticket.getSeatsBooked() > event.getAvailableSeats()) {
            throw new RuntimeException("Not enough seats available");
        }

        event.setAvailableSeats(
                event.getAvailableSeats() - ticket.getSeatsBooked()
        );

        long totalPrice = event.getTicketPrice() * ticket.getSeatsBooked();
        ticket.setTotalPrice(totalPrice);




        eventRepository.save(event);
        ticketRepository.save(ticket);

    return ticket;
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }


}
