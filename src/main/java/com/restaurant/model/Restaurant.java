package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private UUID id;
    private String name;
    private String address;
    private RestaurantType type;
    private List<Meal> mealList = new ArrayList<>();


    public Restaurant(String restaurantName, String restaurantAddress, RestaurantType type) {
        this.id = UUID.randomUUID();
        this.name = restaurantName;
        this.address = restaurantAddress;
        this.type = type;
    }

    public Restaurant(UUID restaurantId, String restaurantName, String restaurantAddress, RestaurantType type) {
        this.id = restaurantId;
        this.name = restaurantName;
        this.address = restaurantAddress;
        this.type = type;
    }

    public Restaurant(UUID restaurantId, String restaurantName, String restaurantAddress, RestaurantType type, List<Meal> mealList) {
        this.id = restaurantId;
        this.name = restaurantName;
        this.address = restaurantAddress;
        this.type = type;
        this.mealList = mealList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setType(RestaurantType type) {
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

    //    @Override
    public String toString() {
        return "Restaurant{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", type = " + type +
                '}';
    }

    public List<Meal> getMealList() {
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
    }
}

