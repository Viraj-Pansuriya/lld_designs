package org.example.models;

import java.util.List;

public class NearestDistanceReqServingStrategy implements RequestingServingStrategy {
    @Override
    public Elevator optimizedElevator(int  destinationFloor, List<Elevator> elevatorList) {
        Elevator elevator = null;

        int minDistance = Integer.MAX_VALUE;
        for(Elevator e : elevatorList){
            int distance = Math.abs(e.getCurrentFloor() - destinationFloor);
            if(distance < minDistance){
                minDistance = distance;
                elevator = e;
            }
        }
        return elevator;
    }
}
