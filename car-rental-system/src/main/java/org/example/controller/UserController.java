package org.example.controller;

import org.example.Exceptions.UserAlreadyExistException;
import org.example.models.CarRentalSystem;
import org.example.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final CarRentalSystem carRentalSystem;

    public UserController(CarRentalSystem carRentalSystem) {
        this.carRentalSystem = carRentalSystem;
    }

    @PostMapping("addUser")
    public User addUser(@RequestBody User user){

        User user1 = carRentalSystem.getUser(user.getUserId());
        if(user1 != null){
            throw new UserAlreadyExistException("User already exists");
        }
        carRentalSystem.getUserList().add(user);
        return user;
    }

    @GetMapping("getUser/{userId}")
    public User getUser(@PathVariable("userId") String userId){
        return carRentalSystem.getUser(userId);
    }

    @GetMapping("getAllUser")
    public List<User> getAllUser(){
        return carRentalSystem.getUserList();
    }

}
