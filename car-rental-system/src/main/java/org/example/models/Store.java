package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    private String storeId;
    private List<Vehicle> vehicleList = new ArrayList<>();
    private List<Registration> registrationList = new ArrayList<>();
    private Location location;

    public Vehicle getVehicle(String vehicleRegistrationNumber){
        return vehicleList.stream()
                .filter(vehicle -> vehicle.getVehicleRegistrationNumber().equals(vehicleRegistrationNumber))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException("Vehicle with Id " + vehicleRegistrationNumber + " not found"));
    }

    public Registration getRegistration(String registrationId){
        return registrationList.stream()
                .filter(registration -> registration.getRegistrationId().equals(registrationId))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException("Registration with Id " + registrationId + " not found"));
    }
}
