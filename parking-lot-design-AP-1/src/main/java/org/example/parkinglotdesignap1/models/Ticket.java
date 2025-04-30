package org.example.parkinglotdesignap1.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Ticket {
    private String ticketId;
    private Spot parkingSpot;
    private Long startTime;


    public static Ticket generateTicket(Spot spot){
        return Ticket.builder()
                .ticketId(UUID.randomUUID().toString())
                .parkingSpot(spot)
                .startTime(System.currentTimeMillis())
                .build();
    }

    public static void validateTicket(Ticket ticket) {
        if(ticket == null || ticket.getParkingSpot() == null || ticket.getParkingSpot().getParkedVehicle() == null){
            throw new RuntimeException("Invalid Ticket");
        }
    }
}
