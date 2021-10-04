package com.restaurant.model;

import java.util.UUID;

public class Meal {

    private final UUID id;
    private String name;
    private float price;

    public Meal(UUID mealId, String name, float price) {
        this.id = mealId;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName(String name) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice(float price) {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}