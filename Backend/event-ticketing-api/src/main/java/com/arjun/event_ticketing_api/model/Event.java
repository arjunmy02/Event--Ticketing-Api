    package com.arjun.event_ticketing_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String location;
    private int availableSeats;
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