package com.arjun.event_ticketing_api.repository;

import com.arjun.event_ticketing_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<Event,Long> {
}
