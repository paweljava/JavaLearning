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

    public void displayRestaurant() {
        System.out.print(name + " ");
        System.out.print(address + " ");
        System.out.println(type);
    };
}

