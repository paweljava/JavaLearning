package com.restaurant.model;

import java.util.UUID;

public class Meal {
    private UUID mealId;
    private String name;
    private float price;

    public Meal (UUID mealId, String name, float price) {
        this.mealId = mealId;
        this.name = name;
        this.price = price;
    }

    public UUID getMealId() {return mealId;}
    public String getName(String name){return name;}
    public void setName(String name) {this.name = name;}
    public float getPrice(float price) {return price;}
    public void setPrice(float price) {this.price = price;}

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    //    public void displayMeal() {
//        System.out.print(name + " ");
//        System.out.print(price + " ");
//    };
}
