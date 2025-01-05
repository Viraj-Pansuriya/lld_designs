package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRentalSystem {
    private List<Store> storeList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();


    public void addStore(Store store){
        storeList.add(store);
    }

    public Store getStore(String storeId){
        return storeList.stream()
                .filter(store-> store.getStoreId().equals(storeId))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException("Store with Id " + storeId + " not found") );
    }

    public User getUser(String userId){
        return userList.stream()
                .filter(user-> user.getUserId().equals(userId))
                .findFirst().orElseThrow(()-> new ResourceNotFoundException("User with Id " + userId + " not found"));
    }
}
