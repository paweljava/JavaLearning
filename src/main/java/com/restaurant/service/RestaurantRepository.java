package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//CRUD
//Create
//Read
//Update
//Delete
// HOMEWORK Complete CRUD operations
public class RestaurantRepository {

    public final Set<Restaurant> restaurantsList;

    public RestaurantRepository(Set<Restaurant> restaurants) {
        this.restaurantsList = restaurants;
    }

    public RestaurantRepository() {
        this.restaurantsList = new HashSet<>(List.of());
    }

    // Create
    public Restaurant add(Restaurant restaurant) {
        restaurantsList.add(restaurant);
        return restaurant;
    }

    // Read
    public Set<Restaurant> getAllRestaurants() {
        return restaurantsList;
    }

    // Update (only address is possible to change)
    public Restaurant updateRestaurantAddressByName(String restaurantName, String newRestaurantAddress) {
        for (final var restaurant : restaurantsList) {
            if (restaurant.getName().equals(restaurantName)) {
                restaurant.setAddress(newRestaurantAddress);
                //final var newRestaurant = new Restaurant(restaurant.getId(), restaurant.getName(), newRestaurantAddress, restaurant.getType());
                //restaurantsList.remove(restaurant);
                //restaurantsList.add(newRestaurant);
                System.out.println("Restaurant updated");
                return restaurant;
            }
        }
        throw new IllegalStateException();
    }

    // Delete
    public void delete(String name) {
        /*for (final var restaurant : restaurantsList) {
            if (restaurant.getName().equals(name)) {
                restaurantsList.remove(restaurant);
                System.out.println("Restaurant deleted");
            }
        }*/
    }

    public boolean isRestaurantListEmpty() {
        return getAllRestaurants().isEmpty();
    }

    public void updateRestaurantNameAndTypeById(UUID restaurantId, String name, RestaurantType type) {
        for (final var restaurant : getAllRestaurants()) {
            if (restaurant.getId().equals(restaurantId) && name != null && type != null) {
                restaurant.setName(name);
                restaurant.setType(type);
                System.out.println("Restaurant updated");
                return;
            }
        }
        throw new IllegalStateException();
    }
}
