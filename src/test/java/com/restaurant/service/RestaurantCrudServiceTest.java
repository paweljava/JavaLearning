package com.restaurant.service;

import com.restaurant.model.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.restaurant.model.RestaurantType.*;

class RestaurantCrudServiceTest {

    private RestaurantCrudService restaurantCrudService;

    @BeforeEach
    void setUp() {
        var restaurant = new Restaurant(UUID.randomUUID(), "u czarnego", "sosnowa" , POLISH);
        var restaurant2 = new Restaurant(UUID.randomUUID(), "u bialego", "lesna" , FRENCH);
        var restaurant3 = new Restaurant(UUID.randomUUID(), "u zielonego", "ogrodowa" , AMERICAN);
        restaurantCrudService = new RestaurantCrudService(new ArrayList<>(List.of(restaurant, restaurant2, restaurant3)));
    }

    @Test
    void add() {
        // given
        var name = "Kebs";
        var address = "Jalowa 3";
        var restaurantType = POLISH;

        // when
        var result = restaurantCrudService.add(name, address, restaurantType);

        // then
        var expected = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        Assertions.assertEquals(expected.getRestaurantName(), result.getRestaurantName());
        Assertions.assertEquals(expected.getRestaurantAddress(), result.getRestaurantAddress());
        Assertions.assertEquals(expected.getType(), result.getType());
    }

    @Test
    void read() {
        // given

        // when
        var result = restaurantCrudService. getAllRestaurants();
        // then
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void update() {
        // given
       // var name = "Kebs";
        //var address = "Jalowa 3";
        var updateaddress = "Wesola 7";
        //var restaurantType = POLISH;

        // when
        //var result = restaurantCrudService.add(name, address, restaurantType);
        //restaurantCrudService.update()
        // then
        // var expected = new Restaurant(UUID.randomUUID(), name, updateaddress, restaurantType);
    }

    @Test
    void delete() {
        // given
        // when
        // then

    }
}