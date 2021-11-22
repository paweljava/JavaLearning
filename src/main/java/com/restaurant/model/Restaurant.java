package com.restaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private UUID restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private RestaurantType type;
    private List<Meal> mealList = new ArrayList<>();


    public Restaurant(String restaurantName, String restaurantAddress, RestaurantType type) {
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.type = type;
    }
    public Restaurant(UUID restaurantId, String restaurantName, String restaurantAddress, RestaurantType type) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.type = type;
    }

    public Restaurant(UUID restaurantId, String restaurantName, String restaurantAddress, RestaurantType type, List<Meal> mealList) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.type = type;
        this.mealList = mealList;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
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
                ", name = '" + restaurantName + '\'' +
                ", address = '" + restaurantAddress + '\'' +
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

