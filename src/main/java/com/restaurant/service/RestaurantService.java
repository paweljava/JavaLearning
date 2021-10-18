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
            If you want to exit, type “exit”
            If you want to create a restaurant, type “1”, if the user chooses this flow, then
            Type restaurant name
            Type restaurant address
            Type restaurant type[ASIAN, MEDITERRANEAN, FRENCH, AMERICAN, POLISH], nice to have - validation for those values
            Show restaurant information is added with the restaurant id(which may be important later)
            If you want to add a meal to a restaurant, type “2”, if the user chooses this flow, then
            Type meal name
            Type meal price
            Type restaurant id to add the meal to particular restaurant
            If user types “3” Show all restaurants, if user chooses this flow, then all restaurants should be printed
            If user types “4”,  shows all meals in a particular restaurant , if user chooses this flow, then
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

    private final List<Restaurant> restaurantsList = new ArrayList<>(List.of());
    private final List<Meal> mealList = new ArrayList<>(List.of());
    //  private Map<UUID, Meal> idToMeal = new HashMap<>();
    private Set<String> abc = new HashSet<>();
    private final RestaurantCrudService restaurantCrudService;


    // Restaurant restaurants = new Restaurant;
    //private UUID restaurantId;
    public RestaurantService(RestaurantCrudService restaurantCrudService) {
        this.restaurantCrudService = restaurantCrudService;
    }

    public void process() {
        Scanner read = new Scanner(System.in);
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

        UUID restaurantId = UUID.randomUUID();// Jak nie inicjalizowac nulla ?
        System.out.print("Type restaurant name: ");
        var name = read.nextLine();
        System.out.print("Type restaurant address: ");
        var address = read.nextLine();
        RestaurantType type = RestaurantType.ASIAN;// RestaurantType type; Jak nie inicjalizowac nulla ?

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
                //resolvedType = RestaurantType.valueOf(consoleInputType);
            }
        }

        // Creation
        // var restaurant = restaurantCrudService.add(name, address, resolvedType);
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

    private boolean isValidRestaurantType(String consoleInputType) {
        try {
            RestaurantType.valueOf(consoleInputType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void addMeal(Scanner read) {
        if (restaurantsList.isEmpty()) {
            System.out.println("Restaurants list's is empty, you can't add meal");
            return;
        }
        var mealId = UUID.randomUUID();
        UUID restaurantId = UUID.randomUUID();
        var pairId = UUID.randomUUID();
        System.out.print("Type meal name: ");
        var name = read.nextLine();
        System.out.print("Type meal price: ");
        var price = read.nextFloat();

        System.out.println("Available restaurants and id's: ");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant));
        System.out.print("Type restaurant id to add the meal to particular restaurant: ");

        // HOMEWORK - validate if uuid is correct, is assigned to restaurant and assign to requested restaurant
        var isIncorrectId = true;
        while (isIncorrectId) {
            var consoleInputId = read.nextLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIncorrectId =  false;
                restaurantId = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }

        var meal = new Meal(restaurantId, name, price);
        mealList.add(meal);
        //  idToMeal.put(restaurantId, meal);
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
        for (Restaurant restaurant : restaurantsList) {
            System.out.println(restaurant);
        }
    }

    public void showMeals(Scanner read) {

        if (restaurantsList.isEmpty()) {
            System.out.println("Restaurants list's is empty, you can't add meal");
            return;
        }
        if (mealList.isEmpty()) {
            System.out.println("Meals list is empty");
            return;
        }

        System.out.println("Restaurants id's: ");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
        System.out.print("Type restaurant id: ");
        UUID id = UUID.randomUUID();
        var isIncorrectId = true;
        while (isIncorrectId) {
            var consoleInputId = read.nextLine();
            if (isValidRestaurantId(consoleInputId)) {
                isIncorrectId =  false;
                 id = UUID.fromString(consoleInputId);
            } else System.out.print("Type correct id: ");
        }

        var a = id;
        System.out.println(id);
        System.out.println("All meals of restaurant id: ");
        var i = 0;
        for (Meal value : mealList) {
            if (id.equals(mealList.get(i).getMealId())) {
                System.out.println(mealList.get(i).getName());
                System.out.println(mealList.get(i).getPrice());
            }
            i++;
        }

       /* if (mealList.contains(id)) {
            System.out.println(mealList);
        } else {
        System.out.println("lista restauracji jest pusta");
        restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
    }*/

        /*if (mealList.contains(idToMeal.get(id))){
            System.out.println(idToMeal);
        } else {
            // mealList.forEach(meal -> System.out.println(idToMeal));
            System.out.println("lista restauracji jest pusta");
            restaurantsList.forEach(restaurant -> System.out.println(restaurant.getRestaurantId()));
        }*/
    }
}