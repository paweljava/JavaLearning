package com.restaurant;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import com.restaurant.service.RestaurantInputValidator;
import com.restaurant.service.RestaurantRepository;
import com.restaurant.service.RestaurantService;

import java.util.HashSet;
import java.util.List;
// OOP
// DS

public class RestaurantConsoleApp {

    public static void main(String[] args) {
        final var restaurants = new HashSet<Restaurant>(List.of(new Restaurant("Ciastkarnia", "Warszawa", RestaurantType.POLISH)));
        final var service = new RestaurantService(new RestaurantRepository(restaurants), new RestaurantInputValidator());
        service.process();
    }
}