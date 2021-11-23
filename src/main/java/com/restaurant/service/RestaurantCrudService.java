package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.*;

//CRUD
//Create
//Read
//Update
//Delete
// HOMEWORK Complete CRUD operations
public class RestaurantCrudService {

    public RestaurantCrudService(Set<Restaurant> restaurants){
        this.restaurantsList = restaurants;
    }

    public RestaurantCrudService() {
        this.restaurantsList = new HashSet<>(List.of());
    }
    private final Set<Restaurant> restaurantsList;

    // Create
    public Restaurant add (String name, String address, RestaurantType restaurantType) {
        final var restaurant = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        restaurantsList.add(restaurant);
        return restaurant;
    }

    // Read
    public Set<Restaurant> getAllRestaurants () {
        return restaurantsList;
    }

    // Update (only address is possible to change)
    public Restaurant update (String restaurantName, String newRestaurantAddress) {
        Restaurant newRestaurant = new Restaurant(UUID.randomUUID(), "Hello", "World", RestaurantType.ASIAN);
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                newRestaurant = new Restaurant(restaurant.getRestaurantId(), restaurant.getRestaurantName(), newRestaurantAddress, restaurant.getType());
                restaurantsList.remove(restaurant);
                restaurantsList.add(newRestaurant);
                System.out.println(newRestaurant);
                return newRestaurant;
            }
        }
        return newRestaurant;
    }

    // Delete
    public Boolean delete (String name) {
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantName().equals(name)) {
                restaurantsList.remove(restaurant);
                System.out.println("Restaurant deleted");
                return true;
            }
        }
        return false;
    }
}
