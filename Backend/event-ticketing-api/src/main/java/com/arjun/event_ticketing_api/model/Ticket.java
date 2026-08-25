package com.arjun.event_ticketing_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity

public class Ticket {


    private Long eventId;
    @Id
    @GeneratedValue
    private Long  ticketId;
    private Long  bookedAt;
    private Long totalPrice;

    private String customerName;

    @Min(value = 1, message = "Seats booked must be at least 1")
    private int seatsBooked;


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public Long getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Long bookedAt) {
        this.bookedAt = bookedAt;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
