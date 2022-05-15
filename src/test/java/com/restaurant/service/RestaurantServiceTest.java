package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RestaurantServiceTest {
    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    private final RestaurantInputValidator restaurantInputValidator = mock(RestaurantInputValidator.class);
    private final RestaurantService restaurantService = new RestaurantService(restaurantRepository, restaurantInputValidator);

    @Test
    void should_add_meal() {
        //given
        final var restaurantId = UUID.randomUUID();
        final var restaurant = new Restaurant(restaurantId, "Kotleciarnia", "Wielicka 1", RestaurantType.POLISH);
        given(restaurantInputValidator.readLine()).willReturn("Kotlet", restaurantId.toString());
        given(restaurantInputValidator.getPrice()).willReturn(12f);
        given(restaurantInputValidator.getId()).willReturn(restaurantId);
        given(restaurantRepository.getAllRestaurants())
                .willReturn(Set.of(restaurant));

        //when
        restaurantService.addMeal();

        //then
        assertEquals("Kotlet", restaurant.getMealList().get(0).getName());
    }

    @Test
    void should_delete_restaurant() {
        //given
        var restaurantId = UUID.randomUUID();
        var restaurant = new Restaurant(restaurantId, "Kebab", "Krakow", RestaurantType.POLISH);
        var restaurant2 = new Restaurant(restaurantId, "Kebab2", "Krakow", RestaurantType.POLISH);
        var restaurant3 = new Restaurant(restaurantId, "Kebab3", "Krakow", RestaurantType.POLISH);
        given(restaurantInputValidator.readLine()).willReturn("Kebab2");
        given(restaurantRepository.getAllRestaurants()).willReturn(new HashSet<>(Set.of(restaurant, restaurant2, restaurant3)));

        //when
        restaurantService.delete();

        //then
        assertFalse(restaurantRepository.getAllRestaurants().isEmpty());
        assertFalse(restaurantRepository.getAllRestaurants().contains(restaurant2));
        assertTrue(restaurantRepository.getAllRestaurants().contains(restaurant));
        assertTrue(restaurantRepository.getAllRestaurants().contains(restaurant3));
        assertFalse(restaurant.getType().toString().isEmpty());
        assertEquals("Kebab", restaurant.getName());
    }

    @Test
    void should_add_restaurant() {
        //given
        var restaurant = new Restaurant("Kawiarnia", "Kwiatowa", RestaurantType.AMERICAN);
        given(restaurantRepository.getAllRestaurants()).willReturn(new HashSet<>(Set.of(restaurant)));

        //when
        restaurantService.add("Pierogarnia", "Katowice", RestaurantType.MEDITERRANEAN);

        //then
        //assertEquals("Pierogarnia", restaurantRepository.restaurantsList.g);
        assertEquals("Pierogarnia", restaurant.getName());
        assertEquals("Katowice", restaurant.getAddress());
        assertEquals(RestaurantType.MEDITERRANEAN, restaurant.getType());
    }
}
