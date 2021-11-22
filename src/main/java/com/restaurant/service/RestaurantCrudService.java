package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

//CRUD
//Create
//Read
//Update
//Delete
// HOMEWORK Complete CRUD operations
public class RestaurantCrudService {

    public RestaurantCrudService(List<Restaurant> restaurants){
        this.restaurantsList = restaurants;
    }

    public RestaurantCrudService() {
        this.restaurantsList = new ArrayList<>(List.of());
    }

    private final List<Restaurant> restaurantsList;
    // Create
    public Restaurant add (String name, String address, RestaurantType restaurantType) {
        final var restaurant = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        restaurantsList.add(restaurant);
        return restaurant;
    }

    // Read
    public List<Restaurant> getAllRestaurants () {
        return restaurantsList;
    }

    // Update (only address is possible to change)
    public Restaurant update () {
        var id = UUID.randomUUID();
        Restaurant restaurantupdated;
        //var restaurant = restaurantsList.set(0, new Restaurant(UUID.randomUUID(), name, address, restaurantType));
        var i=0;
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantId().equals(id)) {
                //restaurantupdated = cos tu trzeba wpisac;
            }
            updateaddress = restaurantsList.get(1).getRestaurantAddress();
        }
        return restaurantupdated;
    }

    // Delete
    public void delete () {
        var id = UUID.randomUUID();
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantId().equals(id)) {
                restaurantsList.
            };
        }

        //var restaurant = restaurantsList.set(0, new Restaurant(UUID.randomUUID(), name, address, restaurantType));
        //var restaurant = restaurantsList.get(1).

        return;

    }

}
