package com.restaurant.model;

import java.util.UUID;

public class Restaurant {

    private UUID id;
    private String name;
    private String address;
    private RestaurantType type;

    public Restaurant(UUID id, String name, String address, RestaurantType type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public UUID getId() {
        return id;
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

    public void displayRestaurant() {
        System.out.print(id + " ");
        System.out.print(name + " ");
        System.out.print(address + " ");
        System.out.println(type);
    };

}

