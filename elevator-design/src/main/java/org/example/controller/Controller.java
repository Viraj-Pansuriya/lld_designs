package org.example.controller;

import org.example.models.Direction;
import org.example.models.ElevatorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ElevatorController elevatorController;

    public Controller(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

    @PostMapping("/addExternalRequest/{sourceFloor}/{direction}")
    public String addExternalRequest(@PathVariable("sourceFloor") String sourceFloor , @PathVariable("direction") Direction direction) {
        long elevatorId = this.elevatorController.addExternalRequest(Integer.parseInt(sourceFloor) , direction);
        return "Elevator " + elevatorId + " is serving the request";
    }

    @PostMapping("/addInternalRequest/{elevatorNumber}/{destinationFloor}")
    public void addInternalRequest(@PathVariable("elevatorNumber") int elevatorNumber , @PathVariable("destinationFloor") int destinationFloor){
        this.elevatorController.addInternalRequest(elevatorNumber , destinationFloor);

    }
}
