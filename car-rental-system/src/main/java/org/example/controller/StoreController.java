package org.example.controller;

import org.example.Exceptions.StoreAlreadyExistException;
import org.example.Exceptions.VehicleAlreadyExistException;
import org.example.models.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("store")
public class StoreController {

    private final CarRentalSystem carRentalSystem;

    public StoreController(CarRentalSystem carRentalSystem) {
        this.carRentalSystem = carRentalSystem;
    }

    @GetMapping("getStore/{storeId}")
    public Store getStore(@PathVariable("storeId") String storeId){
        return carRentalSystem.getStoreList().stream()
                .filter(store-> store.getStoreId().equals(storeId))
                .findFirst().orElse(null);
    }

    @GetMapping("getAllStore")
    public List<Store> getAllStore(){
        return carRentalSystem.getStoreList();
    }

    // add store

    @PostMapping("addStore")
    public List<Store> addStore(@RequestBody Store store){

        boolean isAlreadyExists = carRentalSystem.getStoreList().stream()
                .anyMatch(s-> s.getStoreId().equals(store.getStoreId()));
        if(isAlreadyExists){
            throw new StoreAlreadyExistException("Store with Id " + store.getStoreId() + " already exists");
        }
        carRentalSystem.addStore(store);
        return carRentalSystem.getStoreList();

    }

    @PostMapping("addCar/{storeId}")
    public Store addVehicle(@PathVariable("storeId") String storeId,@RequestBody Car vehicle){

        Store store = carRentalSystem.getStore(storeId);
        if(store == null){
            throw new RuntimeException("Store not found");
        }
        boolean isAlreadyExists = store.getVehicleList().stream()
                .anyMatch(v-> v.getVehicleRegistrationNumber().equals(vehicle.getVehicleRegistrationNumber()));
        if(isAlreadyExists){
            throw new VehicleAlreadyExistException("Vehicle with Id " + vehicle.getVehicleRegistrationNumber() + " already exists");
        }
        store.getVehicleList().add(vehicle);

        return store;

    }


    @GetMapping("getAllVehicles/{storeId}")
    public List<Vehicle> getAllVehicles(@PathVariable("storeId") String storeId){
        return carRentalSystem.getStore(storeId).getVehicleList();
    }

    @GetMapping("getAllRegistration/{storeId}")
    public List<Registration> getAllRegistration(@PathVariable("storeId") String storeId){
        return carRentalSystem.getStore(storeId).getRegistrationList();
    }

    // add vehicle

    // add registration




}
