package org.example.parkinglotdesignap1.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spot {

    private int spotNumber;
    private int price;
    private VehicleType supportedVehicleType;
    private Vehicle parkedVehicle;

    public Spot(int spotNumber , int price , VehicleType supportedVehicleType){
        this.spotNumber = spotNumber;
        this.price = price;
        this.supportedVehicleType = supportedVehicleType;
    }



    public synchronized boolean isEmpty(){
        return (this.parkedVehicle == null);
    }

    public synchronized Ticket parkVehicle(Vehicle vehicle) {
        if(isEmpty() && this.supportedVehicleType == vehicle.getVehicleType()){
            this.parkedVehicle = vehicle;
            return Ticket.generateTicket(this);
        }
        return null;
    }

    public synchronized void unParkVehicle(Vehicle vehicle) {
        if(vehicle == null) {
            throw  new RuntimeException("Vehicle cannot be null");
        }
        if(this.parkedVehicle == vehicle){
            this.parkedVehicle = null;
        }
    }
}
