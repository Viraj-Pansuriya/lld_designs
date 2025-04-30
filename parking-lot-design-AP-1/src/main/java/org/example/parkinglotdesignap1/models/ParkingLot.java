package org.example.parkinglotdesignap1.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

    List<Building> buildingList;
    private static ParkingLot parkingLot;

    private ParkingLot() {
        this.buildingList = new ArrayList<>();
    }

    // singleton pattern.
    public static synchronized ParkingLot getParkingLot(){
        if(parkingLot == null){
            parkingLot = new ParkingLot();
        }
        return parkingLot;
    }

    public Ticket parkVehicle(Vehicle vehicle){
        Ticket ticket = null;
        for(Building building : buildingList){
            ticket = building.parkVehicle(vehicle);
            if(ticket != null){
                break;
            }
        }
        return ticket;
    }

    public double unParkVehicle(Ticket ticket){
        Ticket.validateTicket(ticket);
        ticket.getParkingSpot().unParkVehicle(ticket.getParkingSpot().getParkedVehicle());
        long diff = System.currentTimeMillis() - ticket.getStartTime();
        return ((double)(diff)/(1000*60*60))*ticket.getParkingSpot().getPrice();
    }


    public void addBuilding(Building building) {
        this.buildingList.add(building);
    }

    public void removeBuilding(Building building) {
        this.buildingList.remove(building);
    }

}
