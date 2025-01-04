package org.example.models;

import java.util.List;

public interface RequestingServingStrategy {

    Elevator optimizedElevator(int destinationFloor, List<Elevator> elevatorList);
}
