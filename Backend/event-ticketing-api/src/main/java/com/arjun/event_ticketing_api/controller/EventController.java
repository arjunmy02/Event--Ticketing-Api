package com.arjun.event_ticketing_api.controller;

import com.arjun.event_ticketing_api.model.Event;
import com.arjun.event_ticketing_api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public Event addEvent(@Valid @RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody Event updatedevent) {
        return eventService.updateEvent(id, updatedevent);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }
}