package org.example;

import org.example.models.ElevatorController;
import org.example.models.NearestDistanceReqServingStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Elevator System is running...");
    }

    @Bean
    public ElevatorController elevatorController() {
        // Initialize ElevatorController with 5 elevators and capacity of 5 using NearestDistanceReqServingStrategy
        return new ElevatorController(5, 5, new NearestDistanceReqServingStrategy());
    }
}
