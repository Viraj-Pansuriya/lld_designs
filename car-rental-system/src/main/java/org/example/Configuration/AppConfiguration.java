package org.example.Configuration;

import org.example.models.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public CarRentalSystem carRentalSystem(){

        Store store = new Store();

        Vehicle vehicle1 = Car.builder().
                vehicleRegistrationNumber("GJ03JR2222").
                companyName("Hyundai").
                model("Grand i10").
                vehicleType(VehicleType.CAR).
                hourlyRate(100.0).
                dailyRate(1000.0).
                available(true).
                vehicleStatus(VehicleStatus.ACTIVE).
                hasSunRoof(true).
                numberOfSheets(4).
                build();

        Vehicle vehicle2 = Bike.builder().
                vehicleRegistrationNumber("GJ03JR2223").
                companyName("Honda").
                model("Activa 6G").
                vehicleType(VehicleType.BIKE).
                hourlyRate(50.0).
                dailyRate(500.0).
                available(true).
                vehicleStatus(VehicleStatus.ACTIVE).
                build();

        store.setStoreId("store1");
        store.getVehicleList().add(vehicle1);
        store.getVehicleList().add(vehicle2);
        store.setLocation(new Location());
        store.getLocation().setCity("Bengaluru");
        store.getLocation().setState("Karnataka");
        store.getLocation().setAddress("Green Glen layout , Bellandur");
        store.getLocation().setZipCode("560103");
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        carRentalSystem.getStoreList().add(store);


        User user1 = User.builder().
                userName("John").
                userId("7777").
                drivingLicenseNumber("1234").
                build();

        User user2 = User.builder().
                userName("Jane").
                userId("7778").
                drivingLicenseNumber("1235").
                build();

        carRentalSystem.getUserList().add(user1);
        carRentalSystem.getUserList().add(user2);
        return carRentalSystem;

    }



}
