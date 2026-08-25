    package com.arjun.event_ticketing_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Location is required")
    private String location;

    @Min(value = 1, message = "Available seats must be at least 1")
    private int availableSeats;

    @Min(value = 0, message = "Ticket price cannot be negative")
    private Long ticketPrice;


    public Event() {
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {

        return id;
    }


    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {

        this.eventName = eventName;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }


    public String getLocation() {

        return location;
    }


    public void setAvailableSeats(int availableSeats) {

        this.availableSeats = availableSeats;
    }



    public int getAvailableSeats() {

        return availableSeats;
    }


    public  void  setTicketPrice(Long ticketPrice){
        this.ticketPrice= ticketPrice;

    }

    public  Long getTicketPrice(){
        return ticketPrice;
    }
}