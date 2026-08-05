package com.arjun.event_ticketing_api.service;

import com.arjun.event_ticketing_api.model.Event;
import com.arjun.event_ticketing_api.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getAllEvents(){

        return eventRepository.findAll();
    }
    public Event addEvent(Event event){
        return eventRepository.save(event);

    }

    public Event updateEvent(Long id, Event updatedevent) {
        Event event = eventRepository.findById(id).orElse(null);

        event.setEventName(updatedevent.getEventName());
        event.setLocation(updatedevent.getLocation());
        event.setAvailableSeats(updatedevent.getAvailableSeats());
        event.setTicketPrice(updatedevent.getTicketPrice());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event patchEvent(Long id, Event updatedEvent) {

        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            return null;
        }

        if (updatedEvent.getEventName() != null) {
            event.setEventName(updatedEvent.getEventName());
        }

        if (updatedEvent.getLocation() != null) {
            event.setLocation(updatedEvent.getLocation());
        }

        if (updatedEvent.getAvailableSeats() != 0) {
            event.setAvailableSeats(updatedEvent.getAvailableSeats());
        }

        if (updatedEvent.getTicketPrice() != null) {
            event.setTicketPrice(updatedEvent.getTicketPrice());
        }

        return eventRepository.save(event);
    }
}
