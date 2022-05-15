package com.restaurant.model;

import java.util.UUID;

public class Meal {
    private UUID id = UUID.randomUUID();
    private String name;
    private float price;

    public Meal(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override // po co jest to nadpisywanie ?
    public String toString() {
        return "Meal{" +
                "mealId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
