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
    public void update (UUID id, String newRestaurantAddress) {
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantId().equals(id)) {
                var newRestaurant = restaurant; //Local variable 'newRestaurant' is redundant ? Dlaczego taka podpowiedz
                restaurantsList.remove(restaurant);
                restaurantsList.add(new Restaurant(newRestaurant.getRestaurantId(), newRestaurant.getRestaurantName(), newRestaurantAddress, newRestaurant.getType()));
            }
        }
    }

    // Delete
    public void delete (UUID id) {
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantId().equals(id)) {
                restaurantsList.remove(restaurant);
            }
        }
    }
}
