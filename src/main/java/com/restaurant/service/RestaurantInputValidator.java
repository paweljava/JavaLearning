package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class RestaurantInputValidator {

    private final Scanner scanner = new Scanner(System.in);

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

    public String readLine() {
        return scanner.nextLine();
    }

    public float getCorrectPrice() {
        while (true) {
            var consoleInput = readLine();
            if (isValidPrice(consoleInput)) {
                return Float.parseFloat(consoleInput);
            }
        }
    }

    private boolean isValidPrice(String consoleInputFloat) {
        try {
            Float.parseFloat(consoleInputFloat);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Type correct value");
            return false;
        }
    }

    private boolean isValidRestaurantId(String consoleInputId) {
        try {
            UUID.fromString(consoleInputId);// Result of 'UUID.fromString()' is ignored ?
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public UUID correctId(UUID restaurantId) {
        var isIncorrectId = true;
        while (isIncorrectId) {
            //var consoleInputId = restaurantInputValidator.readLine();
            if (isValidRestaurantId(restaurantId.toString())) {
                isIncorrectId = false;
                restaurantId = UUID.fromString(restaurantId.toString());
            } else System.out.print("Type correct id: ");
        }
        return restaurantId;
        //}
    /*private UUID correctId(UUID restaurantId) {
        var isIncorrectId = true;
        while (isIncorrectId) {
            var consoleInputId = restaurantInputValidator.readLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIncorrectId = false;
                restaurantId = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }
        return restaurantId;
    } */
    }

}

