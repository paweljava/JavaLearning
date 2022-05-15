package com.restaurant.service;

import com.restaurant.model.Restaurant;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.restaurant.model.RestaurantType.*;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryTest {

    private static final Restaurant RESTAURANT_1 = new Restaurant(randomUUID(), "U Czarnego", "Sosnowa", POLISH);
    private static final Restaurant RESTAURANT_2 = new Restaurant(randomUUID(), "U Bialego", "Lesna", FRENCH);
    private static final Restaurant RESTAURANT_3 = new Restaurant(randomUUID(), "U Zielonego", "Ogrodowa", AMERICAN);

    private RestaurantRepository restaurantRepository = new RestaurantRepository(new HashSet<>(List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)));

    @Test
    void should_add_restaurant() {
        // given
        final var name = "Kebs";
        final var address = "Jalowa 3";
        final var restaurantType = POLISH;
        final var restaurant = new Restaurant(name, address, restaurantType);

        // when
        final var result = restaurantRepository.add(restaurant);

        // then
        final var expected = new Restaurant(randomUUID(), name, address, restaurantType);
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getAddress(), result.getAddress());
        assertEquals(expected.getType(), result.getType());
    }

    @Test
    void should_get_all_restaurants() {
        // given
        // when
        final var result = restaurantRepository.getAllRestaurants();

        // then
        assertTrue(!result.isEmpty());
        assertEquals(Set.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3), result);
    }

    @Test
    void should_update_restaurant_address_by_name() {
        // given
        final var newAddress = "Wesola 7";

        // when
        final var result = restaurantRepository.updateRestaurantAddressByName(RESTAURANT_1.getName(), newAddress);

        // then
        assertEquals(newAddress, result.getAddress());
        assertEquals(RESTAURANT_1.getId(), result.getId());
        assertEquals(RESTAURANT_1.getName(), result.getName());
        assertEquals(RESTAURANT_1.getType(), result.getType());
    }

    @Test
    void should_throw_exception_when_no_restaurant_found() {
        // given
        final var nonExistingRestaurantName = "U Szarego";
        final var newAddress = "Wesola 7";

        // when
        // then
        assertThrows(IllegalStateException.class, () -> restaurantRepository.updateRestaurantAddressByName(nonExistingRestaurantName, newAddress));
    }

    @Test
    void should_delete_restaurant() {
        // given
        final var name = "U Bialego";

        // when
        // then
        assertDoesNotThrow(() -> restaurantRepository.delete(name));
    } // TEST DO POPRAWY

    @Test
    void should_update_restaurant_name_and_type_by_id() {
        //given
        //when
        restaurantRepository.updateRestaurantNameAndTypeById(RESTAURANT_2.getId(), "U Grubego", AMERICAN);

        //then
        assertEquals("U Grubego", RESTAURANT_2.getName());
        assertEquals(AMERICAN, RESTAURANT_2.getType());
    }

    @Test
    void should_throw_exception_when_restaurant_name_is_null() {
        //given
        //when
        //then
        assertThrows(IllegalStateException.class, () -> restaurantRepository.updateRestaurantNameAndTypeById(RESTAURANT_1.getId(), null, AMERICAN));
    }
}
//maj 15, 2022 6:19:47 PM org.junit.platform.launcher.core.EngineDiscoveryOrchestrator lambda$logTestDescriptorExclusionReasons$7
//INFO: 0 containers and 6 tests were Method or class mismatch
// po wykonaniu testow wyswietla sie to co powyzej, blad czy tak ma byc ???
