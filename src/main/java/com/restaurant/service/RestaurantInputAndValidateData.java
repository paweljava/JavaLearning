package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.Locale;
import java.util.Scanner;

public class RestaurantInputAndValidateData {

    public Restaurant inputRestaurantData(String name, String address, RestaurantType restaurantType) {
        RestaurantType[] restaurantTypes = RestaurantType.values();
        Scanner read = new Scanner(System.in);
        System.out.print("Type restaurant name: ");
        name = read.nextLine();
        System.out.print("Type restaurant address: ");
        address = read.nextLine();
        var type = typeValidation(read, restaurantTypes);
        var restaurantValidated = new Restaurant(name, address, type);
        return (restaurantValidated);
    }

    private RestaurantType typeValidation(Scanner read, RestaurantType[] restaurantTypes) {
        while (true) {
            System.out.print("Type restaurant type. Available types: ");
            for (RestaurantType k : restaurantTypes) {
                System.out.print(k.name() + " ");
            }
            var consoleInputType = read.nextLine().toUpperCase(Locale.ROOT);
            if (isValidRestaurantType(consoleInputType)) {
                return RestaurantType.valueOf(consoleInputType);
                //resolvedType = RestaurantType.valueOf(consoleInputType);
            }
        }
    }

    private boolean isValidRestaurantType(String consoleInputType) {
        try {
            RestaurantType.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
