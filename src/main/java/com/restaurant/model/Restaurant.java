package com.restaurant.model;

import java.util.UUID;

public class Restaurant {

    private UUID restaurantId;
    private String name;
    private String address;
    private RestaurantType type;

    public Restaurant(UUID restaurantId, String name, String address, RestaurantType type) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.type = type;
    }


    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public RestaurantType getType() {
        return type;
    }

 /*   public void displayRestaurant() {
        System.out.print(restaurantId + " ");
        System.out.print(name + " ");
        System.out.print(address + " ");
        System.out.println(type);
    };*/

//    @Override
    public String toString() {
        return "Restaurant{" +
                "id = " + restaurantId +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", type = " + type +
                '}';
    }

}

