package org.example.models;

import java.util.List;
import java.util.Map;

public class NearestDistanceReqServingStrategy implements RequestingServingStrategy {
    @Override
    public Elevator optimizedElevator(int  destinationFloor, List<Elevator> elevatorList, Direction direction) {
        Elevator elevator = null;

        int minDistance = Integer.MAX_VALUE;
        for (Elevator e : elevatorList) {
            int distance = Math.abs(e.getCurrentFloor() - destinationFloor);
            if (e.getCurrentFloor() > destinationFloor && (e.getState() == State.MOVING && e.getDirection() == Direction.UP)) {
                distance += 2 * (e.getTopTOCover() - e.getCurrentFloor());
                if (direction.equals(Direction.UP)) {
                    distance += 2 * (destinationFloor - Math.min(e.getBottomTOCover() , destinationFloor));
                }
            }
            else if (e.getCurrentFloor() < destinationFloor && (e.getState() == State.MOVING && e.getDirection() == Direction.DOWN)) {
                distance += 2 * (e.getCurrentFloor() - e.getBottomTOCover());
                if (direction.equals(Direction.DOWN)) {
                    distance += 2 * (Math.min(e.getTopTOCover() , destinationFloor) - destinationFloor);
                }
            }

            if (distance < minDistance) {
                minDistance = distance;
                elevator = e;
            }
        }
        return elevator;
    }
}
