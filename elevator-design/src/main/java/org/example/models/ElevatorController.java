package org.example.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElevatorController {
    private int numberOfElevators;
    private List<Elevator> elevatorList = new ArrayList<>();
    private int capacity;
    private final RequestingServingStrategy requestingServingStrategy;

    public ElevatorController(int numberOfElevators , int capacity, RequestingServingStrategy requestingServingStrategy) {
        this.numberOfElevators = numberOfElevators;
        this.capacity = capacity;
        this.requestingServingStrategy = requestingServingStrategy;

        for(long i = 0 ; i < this.numberOfElevators ; i++){
            Elevator elevator = new Elevator(i+1 , this.capacity);
            this.elevatorList.add(elevator);
            Thread thread = new Thread(elevator::run);
            thread.start();
        }
    }


    public Long addExternalRequest(int sourceFloor , Direction direction){

        Elevator elevator = this.requestingServingStrategy.optimizedElevator(sourceFloor , this.elevatorList , direction);
        Request request = new Request(elevator.getCurrentFloor() , sourceFloor);
        elevator.addRequest(request);
        elevator.setBottomTOCover(Math.min(elevator.getBottomTOCover() , sourceFloor));
        elevator.setTopTOCover(Math.max(elevator.getTopTOCover() , sourceFloor));

        return elevator.getId();
    }

    public void addInternalRequest(int elevatorNumber, int destinationFloor) {

        Elevator elevator = this.elevatorList.get(elevatorNumber - 1);
        Request request = new Request(elevator.getCurrentFloor(), destinationFloor);
        elevator.setBottomTOCover(Math.min(elevator.getBottomTOCover() , destinationFloor));
        elevator.setTopTOCover(Math.max(elevator.getTopTOCover() , destinationFloor));
        elevator.addRequest(request);
    }
}
