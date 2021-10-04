package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private UUID restaurantId;
    private String name;
    private String address;
    private RestaurantType type;
    private List<Meal> mealList = new ArrayList<>();

    public Restaurant(UUID restaurantId, String name, String address, RestaurantType type) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public Restaurant(String name, String address, RestaurantType type) {
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

    public void addMeal(Meal meal) {
        this.mealList.add(meal);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id = " + restaurantId +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", type = " + type +
                '}';
    }

    public void showAllMeals() {
        // print all meals
    }
}

