package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;
import java.util.*;

public class RestaurantService {

    /*
    2. Create a console restaurant system, which allows you to create a new restaurant and meal for a particular restaurant.
     The restaurant should contain id, name, address, type, Meal should have name, price and id. Ids are assigned by the program during entity creation.
            For program execution use IntellIJ (run or debug program):
            At the beginning the program prints instructions, after each point user input is expected.
            If you want to exit, type exit
            If you want to create a restaurant, type 1, if the user chooses this flow, then
            Type restaurant name
            Type restaurant address
            Type restaurant type[ASIAN, MEDITERRANEAN, FRENCH, AMERICAN, POLISH], nice to have - validation for those values
            Show restaurant information is added with the restaurant id(which may be important later)
            If you want to add a meal to a restaurant, type 2, if the user chooses this flow, then
            Type meal name
            Type meal price
            Type restaurant id to add the meal to particular restaurant
            If user types 3 Show all restaurants, if user chooses this flow, then all restaurants should be printed
            If user types 4,  shows all meals in a particular restaurant , if user chooses this flow, then
            Type restaurant id(should be shown in restaurant print), then all meals of a specific restaurant should be printed
    */

    //  1. Zrozumiec o co chodzi
    //    a) wyjatek -> przerywamy program
    //    b) cofamy sie do menu
    //    c) prosze wpisac ponownie typ z podanych typow - X
    //  2. Wymyslec jak to zrobic
    //    a) sprawdzamy czy nasz typ znajduje sie w liscie typow
    //    b) jesli nie, zmuszamy uzytkonika do nastepnej iteracji wyboru typu
    //    c) jesli tak, kontynuujemy proces
    //  3. Robimy to

    // private final Set<Restaurant> restaurantsList = new HashSet<>(Set.of(new Restaurant(UUID.fromString("e7c3a6a0-1dda-4ea8-a555-64ccf10b347d"), "u grubego", "Warszawa", RestaurantType.ASIAN, List.of())));
    private final Set<Restaurant> restaurantsList = new HashSet<>();
    private final RestaurantCrudService restaurantCrudService;

    public RestaurantService(RestaurantCrudService restaurantCrudService) {
        this.restaurantCrudService = restaurantCrudService;
    }

    public void process() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Type \"exit\" to exit ");
            System.out.println("Type \"1\" to create restaurant ");
            System.out.println("Type \"2\" to add a meal to a restaurant");
            System.out.println("Type \"3\" to show all restaurants");
            System.out.println("Type \"4\" to show all meals in a particular restaurant");
            System.out.print("What is yours choose: ");
            String choose = scanner.nextLine();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> addRestaurant(scanner);
                case "2" -> addMeal(scanner);
                case "3" -> showRestaurants();
                case "4" -> showMeals(scanner);
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

        UUID restaurantId = UUID.randomUUID();
        System.out.print("Type restaurant name: ");
        var restaurantName = read.nextLine();
        System.out.print("Type restaurant address: ");
        var restaurantAddress = read.nextLine();

        // Creation
        var type = typeValidation(read, restaurantTypes);
        var restaurant = new Restaurant(restaurantId, restaurantName, restaurantAddress, type);
        restaurantsList.add(restaurant);

        // Resataurant information
        System.out.println("Restaurant created:");
        System.out.println("Restaurant id: " + restaurantId);
        System.out.println("Name: " + restaurantName);
        System.out.println("Address: " + restaurantAddress);
        System.out.println("Type: " + type);
        System.out.println();
    }

    private RestaurantType typeValidation(Scanner read, RestaurantType[] restaurantTypes) {
        while (true) {
            System.out.print("Type restaurant type. Available types: ");
            for (RestaurantType k : restaurantTypes) {
                System.out.print(k.name() + " ");
            }
            var consoleInputType = read.nextLine().toUpperCase(Locale.ROOT);
            if (isValidRestaurantType(consoleInputType)) {
                return RestaurantType.valueOf(consoleInputType);
                //resolvedType = RestaurantType.valueOf(consoleInputType);
            }
        }
    }

    private boolean isValidRestaurantType(String consoleInputType) {
        try {
            RestaurantType.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void addMeal(Scanner read) {
        if (restaurantListIsEmpty("Restaurants list's is empty, you can't add meal")) return;

        UUID restaurantId = UUID.randomUUID();
        System.out.print("Type meal name: ");
        var name = read.nextLine();
        System.out.print("Type meal price: ");
        var price = getCorrectPrice(read);
        //var price = Float.parseFloat(read.nextLine());
        System.out.println("Available restaurants and id's: ");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant));
        System.out.print("Type restaurant id to add the meal to particular restaurant: ");

        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantId().equals(restaurantId = correctId(read, restaurantId))) {
                var meal = new Meal(UUID.randomUUID(), name, price);
                restaurant.getMealList().add(meal);
            } else System.out.println("Nie dodalo posilku");
        }
    }

    private float getCorrectPrice(Scanner read) {
        while (true) {
            var consoleInput = read.nextLine();
            if (isValidPrice(consoleInput)) {
                return Float.parseFloat(consoleInput);
            }
        }
    }

    private boolean restaurantListIsEmpty(String s) {
        if (restaurantsList.isEmpty()) {
            System.out.println(s);
            return true;
        }
        return false;
    }

    private UUID correctId(Scanner read, UUID restaurantId) {
        var isIncorrectId = true;
        while (isIncorrectId) {
            var consoleInputId = read.nextLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIncorrectId = false;
                restaurantId = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }
        return restaurantId;
    }

    private boolean isValidPrice(String consoleInputFloat) {
        try {
            Float.parseFloat(consoleInputFloat);
            return true;
    }   catch (NumberFormatException e) {
            System.out.println("Type correct value");
            return false;
        }
    }

    private boolean isValidRestaurantId(String consoleInputId) {
        try {
            UUID.fromString(consoleInputId);// Result of 'UUID.fromString()' is ignored ?
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void showRestaurants() {
        if (restaurantListIsEmpty("Restaurants list's is empty")) return;
        for (Restaurant restaurant : restaurantsList) {
                System.out.println(restaurant);
        }
    }

    public void showMeals(Scanner read) {
        if (restaurantListIsEmpty("Restaurants list's is empty")) return;
        System.out.println("Restaurants id's: ");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
        System.out.print("Type restaurant id: ");
        UUID id = UUID.randomUUID();
        id = correctId(read, id);
        System.out.println("All meals of restaurant id: ");

        var i = 0;
        for (Restaurant value : restaurantsList) {
            if ((value.getRestaurantId().equals(id)) && (!(value.getMealList().isEmpty()))){
                for (Meal meal : value.getMealList()) {
                    System.out.print(value.getMealList().get(i).getMealName() + " - ");
                    System.out.println(value.getMealList().get(i).getMealPrice() + " zl");
                    i++;
                }
            } else System.out.println("This restaurant have no meals!");
        }
    }
}