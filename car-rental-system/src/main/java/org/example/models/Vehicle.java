package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Vehicle {
    private String vehicleRegistrationNumber;
    private String companyName;
    private String model;
    private VehicleType vehicleType;
    private double hourlyRate;
    private double dailyRate;
    private boolean available;
    private VehicleStatus vehicleStatus;
}
