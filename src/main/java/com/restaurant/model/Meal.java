package com.restaurant.model;

import java.util.UUID;

public class Meal {
    private UUID mealId = UUID.randomUUID();
    private String mealName;
    private float mealPrice;

    public Meal(UUID mealId, String mealName, float mealPrice) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    public UUID getMealId() {
        return mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public float getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(float mealPrice) {
        this.mealPrice = mealPrice;
    }

    @Override // po co jest to nadpisywanie ?
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", name='" + mealName + '\'' +
                ", price=" + mealPrice +
                '}';
    }


    //    public void displayMeal() {
    //       System.out.print(name + " ");
    //       System.out.print(price + " ");
    //    };
}
