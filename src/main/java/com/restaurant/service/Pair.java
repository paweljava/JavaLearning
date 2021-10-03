package com.restaurant.service;

import java.util.UUID;

public class Pair {
    UUID pairId;
    UUID restaurantId;
    UUID mealId;

    public Pair(UUID pairId, UUID restaurantId, UUID mealId) {
        this.pairId = pairId;
        this.restaurantId = restaurantId;
        this.mealId = mealId;
    }
}
