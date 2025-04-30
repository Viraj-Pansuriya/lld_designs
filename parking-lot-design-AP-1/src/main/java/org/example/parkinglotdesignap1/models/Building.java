package org.example.parkinglotdesignap1.models;

import java.util.ArrayList;
import java.util.List;

public class Building {

    List<Floor> floorList;

    public Building(){
        this.floorList = new ArrayList<>();
    }


    public Ticket parkVehicle(Vehicle vehicle) {
        Ticket ticket = null;
        for(Floor floor : floorList){
            ticket = floor.parkVehicle(vehicle);
            if (ticket != null) break;
        }
        return ticket;
    }

//    public double unParkVehicle(Ticket ticket){
//        double amountToPay = -1;
//        for(Floor floor : floorList){
//            amountToPay = floor.unParkVehicle(ticket);
//            if (amountToPay > 0) break;
//        }
//        return amountToPay;
//
//    }

    public void addFloor(Floor floor){
        floorList.add(floor);
    }

    public void removeFloor(Floor floor){
        floorList.remove(floor);
    }

    public void addFloors(List<Floor> floors){
        floorList.addAll(floors);
    }
}
