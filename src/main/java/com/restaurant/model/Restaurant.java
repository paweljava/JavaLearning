package com.restaurant.model;

public class Restaurant {

    private String name;
    private String address;
    private RestaurantType type;

    public Restaurant(String name, String address, RestaurantType type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }
}
