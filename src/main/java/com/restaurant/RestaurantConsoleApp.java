package com.restaurant;

import com.restaurant.service.RestaurantInputValidator;
import com.restaurant.service.RestaurantRepository;
import com.restaurant.service.RestaurantService;
// OOP
// DS

public class RestaurantConsoleApp {

    public static void main(String[] args) {
        final var service = new RestaurantService(new RestaurantRepository(), new RestaurantInputValidator());
        service.process();
    }
}