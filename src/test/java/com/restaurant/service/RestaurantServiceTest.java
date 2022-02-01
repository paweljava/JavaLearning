package com.restaurant.service;

import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RestaurantServiceTest {
    private RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    private RestaurantInputValidator restaurantInputValidator = mock(RestaurantInputValidator.class);
    private final RestaurantService restaurantService = new RestaurantService(restaurantRepository, restaurantInputValidator);
    @Test
    void should_addMeal() {
        //given
        var restaurantId = UUID.randomUUID();
        var restaurant = new Restaurant(restaurantId, "Kotleciarnia","Wielicka 1", RestaurantType.POLISH);
        given(restaurantInputValidator.readLine()).willReturn("Kotlet", restaurantId.toString());
        given(restaurantInputValidator.getCorrectPrice()).willReturn(12f);
        given(restaurantInputValidator.correctId(restaurantId)).willReturn(restaurantId);

        given(restaurantRepository.getAllRestaurants())
                .willReturn(Set.of(restaurant));

        //when
        restaurantService.addMeal();

        //then
        assertEquals(restaurant.getMealList().get(0).getMealName(), "Kotlet" );
    }
}
