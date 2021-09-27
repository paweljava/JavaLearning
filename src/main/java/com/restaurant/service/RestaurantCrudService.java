package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//CRUD
//Create
//Read
//Update
//Delete
// HOMEWORK Complete CRUD operations
public class RestaurantCrudService {

    public final List<Restaurant> restaurantsList = new ArrayList<>(List.of(
//        new Restaurant("U grubego", "Sucha 13", RestaurantType.POLISH)
    ));

    Restaurant add(String name, String address, RestaurantType restaurantType) {
        final var restaurant = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        restaurantsList.add(restaurant);
        return restaurant;
    }

    //update(only address is possible to change)

    //delete

}
