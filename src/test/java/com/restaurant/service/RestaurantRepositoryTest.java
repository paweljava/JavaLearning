package com.restaurant.service;

import com.restaurant.model.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.restaurant.model.RestaurantType.*;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryTest {

    private RestaurantRepository restaurantRepository;

    @BeforeEach
    void setUp() {
        var restaurant = new Restaurant(UUID.randomUUID(), "U Czarnego", "Sosnowa", POLISH);
        var restaurant2 = new Restaurant(UUID.randomUUID(), "U Bialego", "Lesna", FRENCH);
        var restaurant3 = new Restaurant(UUID.randomUUID(), "U Zielonego", "Ogrodowa", AMERICAN);
        restaurantRepository = new RestaurantRepository(new HashSet<>(List.of(restaurant, restaurant2, restaurant3)));
    }

    @Test
    void should_add() {
        // given
        var name = "Kebs";
        var address = "Jalowa 3";
        var restaurantType = POLISH;

        // when
        var result = restaurantRepository.add(name, address, restaurantType);

        // then
        var expected = new Restaurant(UUID.randomUUID(), name, address, restaurantType);
        assertEquals(expected.getRestaurantName(), result.getRestaurantName());
        assertEquals(expected.getRestaurantAddress(), result.getRestaurantAddress());
        assertEquals(expected.getType(), result.getType());
    }

    @Test
    void should_read() {
        // given
        // when
        var result = restaurantRepository.getAllRestaurants();

        // then
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void should_update() {
        // given
        var name = "U Bialego";
        var newAddress = "Wesola 7";

        // when
        var result = restaurantRepository.update(name, newAddress);

        // then
        var expected = new Restaurant(result.getRestaurantId(), result.getRestaurantName(), newAddress, result.getType());
        // Assertions.assertSame(expected, result);
        // Assertions.assertEquals(expected, result);
        assertEquals(expected.getRestaurantId(), result.getRestaurantId());
        assertEquals(expected.getRestaurantName(), result.getRestaurantName());
        assertEquals(newAddress, result.getRestaurantAddress());
        assertEquals(expected.getType(), result.getType());
    }

    @Test
    void should_throw_exception_when_no_restaurant_found() {
        // given
        String name = "U Szarego";
        String newAddress = "Wesola 7";

        // when
        // then
        assertThrows(IllegalStateException.class, () -> restaurantRepository.update(name, newAddress));
    }

    @Test
    void delete() {
        // given
        String name = "U Bialego";

        // when
        // then
        assertDoesNotThrow(() -> restaurantRepository.delete(name));
    }

    /*@Test
    void should_not_delete_and_throw_exception_when_no_restaurant_found() {
        // given
        String name = "Bialego";

        // when
        // then
        assertThrows(IllegalStateException.class, () -> restaurantCrudService.delete(name));
    }*/
}