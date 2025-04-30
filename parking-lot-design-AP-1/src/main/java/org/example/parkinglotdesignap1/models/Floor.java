package org.example.parkinglotdesignap1.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Floor {

    List<Spot> spots;
    Map<VehicleType , List<Spot>> vehicleTypeWiseSpot; // can be implemented using this Map.  which helps to reduce search time.

    Floor(int numberOfSpots) {

        this.spots = new ArrayList<>();
        int bikeSpot = (int) (numberOfSpots * 0.5);
        int carSpot = (int) (numberOfSpots * 0.3);
        int truckSpot = (int) (numberOfSpots * 0.2);


        int counter = 1;
        for(;counter <= bikeSpot; counter++){
            this.spots.add(new Spot(counter, 5, VehicleType.TWO_WHEELER));
        }
        for(;counter <= bikeSpot + carSpot; counter++){
            this.spots.add(new Spot(counter, 10, VehicleType.FOUR_WHEELER));
        }
        for(;counter <= bikeSpot + carSpot + truckSpot; counter++){
            this.spots.add(new Spot(counter, 15, VehicleType.HEAVY_VEHICLE));
        }
    }


    public Ticket parkVehicle(Vehicle vehicle) {
        Ticket ticket = null;
        for(Spot spot : spots){
            ticket = spot.parkVehicle(vehicle);
            if(ticket != null) break;
        }
        return ticket;
    }

//    public double unParkVehicle(Ticket ticket) {
//        double amountToPay = -1;
//        for(Spot spot : spots){
//            amountToPay = spot.unParkVehicle(ticket);
//            if(amountToPay > 0) break;
//        }
//        return amountToPay;
//    }
}
