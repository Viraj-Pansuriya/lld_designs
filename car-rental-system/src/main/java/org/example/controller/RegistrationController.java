package org.example.controller;

import org.example.Exceptions.InvalidArgumentException;
import org.example.dto.RegistrationDto;
import org.example.models.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    private final CarRentalSystem carRentalSystem;

    public RegistrationController(CarRentalSystem carRentalSystem) {
        this.carRentalSystem = carRentalSystem;
    }


    @PostMapping("createRegistration")
    public Registration createRegistration(@RequestBody RegistrationDto registrationDto){

        LocalDateTime registrationDate = LocalDateTime.now();
        LocalDateTime pickUpDateTime = registrationDto.getPickUpDateTime();
        LocalDateTime dropOffDateTime = registrationDto.getDropOffDateTime();

        if(pickUpDateTime.isAfter(dropOffDateTime)){
            throw new InvalidArgumentException("Pick up date time must be before drop off date time");
        }
        if(pickUpDateTime.isBefore(registrationDate)){
            throw new InvalidArgumentException("Pick up date time must be in a future");
        }
        User user = carRentalSystem.getUser(registrationDto.getUserId());
        Store store = carRentalSystem.getStore(registrationDto.getStoreId());
        Vehicle vehicle = store.getVehicle(registrationDto.getVehicleRegistrationId());


        return generateRegistration(user ,store, vehicle , pickUpDateTime , dropOffDateTime);
    }


    @PostMapping("cancelRegistration/{storeId}/{registrationId}")
    public synchronized Registration cancelRegistration(@PathVariable("registrationId") String registrationId,
                                           @PathVariable("storeId") String storeId){

        Registration registration = carRentalSystem.getStore(storeId).getRegistration(registrationId);
        if(registration.getRegistrationStatus().equals(RegistrationStatus.CANCELLED)){
            throw new InvalidArgumentException("Registration is already cancelled");
        }
        registration.setRegistrationStatus(RegistrationStatus.CANCELLED);
        registration.getVehicle().setAvailable(true);
        return registration;
    }


    // making sure that multiple threads cannot create registration at the same time
    public synchronized Registration generateRegistration(User user , Store store , Vehicle vehicle , LocalDateTime pickUpDateTime , LocalDateTime dropOffDateTime){

        if(!vehicle.isAvailable()){
            throw new InvalidArgumentException("Vehicle is not available");
        }
        String registrationId = "REG_" + UUID.randomUUID();
        Registration registration = Registration.builder().
                registrationId(registrationId).
                user(user).
                vehicle(vehicle).
                registrationDate(LocalDateTime.now()).
                pickUpDateTime(pickUpDateTime).
                dropOffDateTime(dropOffDateTime).
                registrationStatus(RegistrationStatus.SCHEDULED).
                build();

        vehicle.setAvailable(false);
        store.getRegistrationList().add(registration);
        return registration;
    }


    /**
     * Test python code :
     * **/

//            import requests
//        import threading
//
//        # Define your base URL
//            base_url = "https://5a28-103-124-10-57.ngrok-free.app"
//
//                    # Example endpoint to call
//            endpoint = "/registration/createRegistration"
//
//                    # Example request body using the RegistrationDto structure
//                    data = {
//                    "userId": "7777",
//                    "vehicleRegistrationId": "GJ03JR2222",
//                    "storeId": "store1",
//                    "pickUpDateTime": "2025-01-10T10:00:00",  # Adjust to your desired format
//            "dropOffDateTime": "2025-01-15T10:00:00"  # Adjust to your desired format
//        }
//
//        # Set the full URL by combining the base URL and the endpoint
//        url = base_url + endpoint
//
//        # Shared list for storing results (not thread-safe intentionally)
//        results = []
//
//                # Function for sending a POST request
//        def send_request():
//        response = requests.post(url, json=data)
//            if response.status_code == 200:
//        print(f"Success from thread {threading.current_thread().name}: {response.json()}")
//                results.append(response.json())  # Append the response
//            else:
//        print(f"Failed from thread {threading.current_thread().name} with status code: {response.status_code}")
//        print("Response Text:", response.text)
//
//        # Number of threads to test
//                num_threads = 10
//
//        # Create and start threads
//        threads = []
//                for i in range(num_threads):
//        thread = threading.Thread(target=send_request)
//            threads.append(thread)
//            thread.start()
//
//        # Wait for all threads to complete
//        for thread in threads:
//                thread.join()
//
//        # Verify results
//        print("\nResults:")
//        print(f"Total successful registrations: {len(results)}")
//        for result in results:
//        print(result)


}
