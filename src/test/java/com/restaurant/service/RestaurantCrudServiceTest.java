package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class RestaurantCrudServiceTest {

    private final RestaurantCrudService restaurantCrudService = new RestaurantCrudService();

    @Test
    void add() {
        // given
        var name = "Kebs";
        var address = "Jalowa 3";
        var restaurantType = RestaurantType.POLISH;

        // when
        var result = restaurantCrudService.add(name, address, restaurantType);

        // then
        var expected = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        Assertions.assertEquals(expected.getRestaurantName(), result.getRestaurantName());
        Assertions.assertEquals(expected.getRestaurantAddress(), result.getRestaurantAddress());
        Assertions.assertEquals(expected.getType(), result.getType());
    }
}