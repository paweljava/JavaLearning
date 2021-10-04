package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.*;

public class RestaurantService {

    private final List<Restaurant> restaurantsList = new ArrayList<>(List.of());
    private final List<Meal> mealList = new ArrayList<>(List.of());
    private final RestaurantCrudService restaurantCrudService;

    public RestaurantService(RestaurantCrudService restaurantCrudService) {
        this.restaurantCrudService = restaurantCrudService;
    }

    public void process() {
        final var read = new Scanner(System.in);
        while (true) {

            System.out.println("Type \"exit\" to exit ");
            System.out.println("Type \"1\" to create restaurant ");
            System.out.println("Type \"2\" to add a meal to a restaurant");
            System.out.println("Type \"3\" to show all restaurants");
            System.out.println("Type \"4\" to show all meals in a particular restaurant");
            System.out.print("What is yours choose: ");
            String choose = read.nextLine();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> addRestaurant(read);
                case "2" -> addMeal(read);
                case "3" -> showRestaurants();
                case "4" -> showMeals(read);
                default -> System.out.println("Type correct value!");
            }
        }
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }

    public void addRestaurant(Scanner read) {
        RestaurantType[] restaurantTypes = RestaurantType.values();

        final var restaurantId = UUID.randomUUID();
        System.out.print("Type restaurant name: ");
        var name = read.nextLine();
        System.out.print("Type restaurant address: ");
        var address = read.nextLine();
        RestaurantType type = RestaurantType.ASIAN;

        // Validation
        var isIncorrectType = true;
        while (isIncorrectType) {
            System.out.print("Type restaurant type. Available types: ");
            for (RestaurantType k : restaurantTypes) {
                System.out.print(k.name() + " ");
            }
            var consoleInputType = read.nextLine().toUpperCase(Locale.ROOT); // Co robi (Locale.ROOT) ?
            if (isValidRestaurantType(consoleInputType)) {
                isIncorrectType = false;
                type = RestaurantType.valueOf(consoleInputType);
            }
        }

        // Creation
        var restaurant = new Restaurant(restaurantId, name, address, type);
        restaurantsList.add(restaurant);

        // Resataurant information
        System.out.println("Restaurant created:");
        System.out.println("Restaurant id: " + restaurantId);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Type: " + type);
        System.out.println();
    }

    public void addMeal(Scanner scanner) {
        if (isRestaurantListEmpty()) return;

        System.out.print("Type meal name: ");
        var name = scanner.nextLine();

        System.out.print("Type meal price: ");
        var price = scanner.nextFloat();

        System.out.print("Type meal type: ");
        var type = scanner.nextLine();

        System.out.println("Available restaurants and id's: ");
        restaurantsList.forEach(System.out::println);
        System.out.print("Type restaurant id to add the meal to particular restaurant: ");

        var restaurantId = restaurantId(scanner);
        System.out.println("Correct id entered" + restaurantId); // dla mnie
        final var foundRestaurant = findRestaurantById(restaurantId);
        final var meal = new Meal(UUID.randomUUID(), name, price);
        foundRestaurant.ifPresent(r -> {
            r.addMeal(meal);
            System.out.println("Meal added");
        });
    }

    private boolean isValidRestaurantType(String consoleInputType) {
        try {
            RestaurantType.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) { // Co znaczy e ?

            return false;
        }
    }

    private UUID restaurantId(Scanner read) {
        UUID result = null;
        var isIncorrectId = true;
        while (isIncorrectId) {
            var consoleInputId = read.nextLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIncorrectId = false;
                result = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }
        return result;
    }

    private boolean isRestaurantListEmpty() {
        if (restaurantsList.isEmpty()) {
            System.out.println("Restaurants list's is empty, you can't add meal");
            return true;
        }
        return false;
    }

    private boolean isValidRestaurantId(String consoleInputId) {
        try {
            UUID.fromString(consoleInputId);// Result of 'UUID.fromString()' is ignored ?
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showRestaurants() {
        for (Restaurant restaurant : restaurantsList) {
            System.out.println(restaurant);
        }
    }

    public void showMeals(Scanner read) {

        System.out.println("Restaurants id's: ");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
        System.out.print("Type restaurant id: ");
        UUID restaurantId = null;
        var isIdNotFilled = true;
        while (isIdNotFilled) {
            var consoleInputId = read.nextLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIdNotFilled = false;
                restaurantId = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }
        Optional<Restaurant> foundRestaurant = findRestaurantById(restaurantId);
        if (foundRestaurant.isPresent()) {
            foundRestaurant.get().showAllMeals();
        } else {
            System.out.printf("Restaurant with id %s not found%n", restaurantId);
        }

        System.out.println("List meals of restaurant id: ");
        mealList.forEach(System.out::println);
        System.out.println("lista restauracji ponownie w 4");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
    }

    private Optional<Restaurant> findRestaurantById(UUID restaurantId) {
        var restaurants = restaurantCrudService.restaurantsList;
        Optional<Restaurant> foundRestaurant = Optional.empty();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getRestaurantId().equals(restaurantId)) {
                foundRestaurant = Optional.ofNullable(restaurants.get(i));
            }
        }
        return foundRestaurant;
    }
}
