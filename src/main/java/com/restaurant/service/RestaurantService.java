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

    private final RestaurantRepository restaurantRepository;
    private final RestaurantInputValidator restaurantInputValidator;


    public RestaurantService(RestaurantRepository restaurantRepository, RestaurantInputValidator restaurantInputValidator) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantInputValidator = restaurantInputValidator;
    }

    public void process() {
        final var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Application menu");
            System.out.println("Type \"exit\" to exit ");
            System.out.println("Type \"1\" to create restaurant ");
            System.out.println("Type \"2\" to add a meal to a restaurant");
            System.out.println("Type \"3\" to show all restaurants");
            System.out.println("Type \"4\" to show all meals in a particular restaurant");
            System.out.println("Type \"5\" to edit restaurant address by name");
            System.out.println("Type \"6\" to edit restaurant name and type by id");
            System.out.println("Type \"7\" to delete restaurant ");
            System.out.println();
            System.out.print("What is yours choice: ");
            var choice = scanner.nextLine();

            switch (choice) {
                case "exit" -> exit();
                case "1" -> addRestaurant();
                case "2" -> addMeal();
                case "3" -> showRestaurants();
                case "4" -> showMeals();
                case "5" -> editRestaurantAddressByName();
                case "6" -> editRestaurantNameAndTypeById();
                case "7" -> delete();
                default -> System.out.println("Type correct value!");
            }
        }
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }

    public void addRestaurant() {
        System.out.print("Type restaurant name: ");
        final var restaurantName = restaurantInputValidator.readLine();
        System.out.print("Type restaurant address: ");
        final var restaurantAddress = restaurantInputValidator.readLine();

        // Creation
        final var type = getRestaurantType();
        final var restaurant = new Restaurant(restaurantName, restaurantAddress, type);
        restaurantRepository.getAllRestaurants().add(restaurant);

        // Resataurant information
        System.out.println("Restaurant created:");
        System.out.println("Restaurant id: " + restaurant.getId());
        System.out.println("Name: " + restaurantName);
        System.out.println("Address: " + restaurantAddress);
        System.out.println("Type: " + type);
        System.out.println();
    }

    private RestaurantType getRestaurantType() {
        while (true) {
            System.out.println("Type restaurant type. Available types: ");
            Arrays.stream(RestaurantType.values()).forEach(System.out::println); // <-- to jest stream zastepujacy ponizej petle for
            //for (RestaurantType k : RestaurantType.values()) {
            //    System.out.print(k.name() + " ");
            //}
            final var consoleInputType = restaurantInputValidator.readLine().toUpperCase(Locale.ROOT);
            if (isValidRestaurantType(consoleInputType)) {
                return RestaurantType.valueOf(consoleInputType);
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

    private boolean isRestaurantListEmpty2() {
        if (restaurantRepository.isRestaurantListEmpty()) {
            System.out.println("Restaurants list's is empty");
            System.out.println();
            return true;
        }   else return false;
    }

    public void addMeal() {
        /*if (restaurantRepository.isRestaurantListEmpty()) {
            System.out.println("Restaurants list's is empty, you can't add meal");
            return;
        }*/
        if (isRestaurantListEmpty2()) {return;}
        System.out.print("Type meal name: ");
        final var name = restaurantInputValidator.readLine();
        System.out.print("Type meal price: ");
        final var price = restaurantInputValidator.getPrice();
        System.out.println("Available restaurants and id's: ");
        showRestaurants();
        System.out.print("Type restaurant id to add the meal to particular restaurant: ");

        final var restaurantId = restaurantInputValidator.getId();
        for (Restaurant restaurant : restaurantRepository.getAllRestaurants()) {
            if (restaurant.getId().equals(restaurantId)) {
                final var meal = new Meal(name, price);
                restaurant.getMealList().add(meal);
                System.out.println("Added meal to restaurant");
                System.out.println();
                return;
            }
        }
        System.out.println("Nie dodalo posilku");
    }

    public void showRestaurants() {
        /*if (restaurantRepository.isRestaurantListEmpty()) {
            System.out.println("Restaurants list's is empty");
            return;
        }*/
        if (isRestaurantListEmpty2()) {return;}
        for (Restaurant restaurant : restaurantRepository.getAllRestaurants()) {
            System.out.println(restaurant);
        }
        System.out.println();
    }

    public void showMeals() {
        /*if (restaurantRepository.isRestaurantListEmpty()) {
            System.out.println("Restaurants list's is empty");
            return;
        }*/
        if (isRestaurantListEmpty2()) {return;}
        System.out.println("Restaurants id's: ");
        restaurantRepository.getAllRestaurants().forEach(restaurant -> System.out.println(restaurant.getId()));
        System.out.print("Type restaurant id: ");
        final var id = restaurantInputValidator.getId();
        System.out.println("All meals of restaurant id: ");

        for (final var restaurant : restaurantRepository.getAllRestaurants()) {
            if (restaurant.getId().equals(id) && !(restaurant.getMealList().isEmpty())) {
                for (final var meal : restaurant.getMealList()) {
                    System.out.print(meal.getName() + " - ");
                    System.out.println(meal.getPrice() + " zl");
                }
                return;
            }
        }
        System.out.println("This restaurant have no meals!");
    }

    //  Metoda ma zmieniac name i type jesli restauracja istnieje w pamieci a jesli nie to ma byc wyrzucony wyjatek
    //  2 scenariusze
    // 1. Jesli restauracja istnieje
    // 2. Jesli restauracja nie istnieje
    // 3. Dokladamy nulle do metody editRestaurant i testujemy wyjatki.
    // 4. Przetestowac metode delete.

    public void editRestaurantNameAndTypeById() {
        showRestaurants();
        System.out.println("Type restaurant id: ");
        final var restaurantId = UUID.fromString(restaurantInputValidator.readLine());
        System.out.println("Type new restaurant name: ");
        final var name = restaurantInputValidator.readLine();
        System.out.println("Type new restaurant type: ");
        final var type = getRestaurantType();
        restaurantRepository.updateRestaurantNameAndTypeById(restaurantId, name, type);
    }

    public void delete() {
        var restaurantsList = restaurantRepository.getAllRestaurants();
        System.out.print("Type restaurant name: ");
        var restaurantName = restaurantInputValidator.readLine();
        /*if (restaurantsList.stream().filter(r -> r.getRestaurantName().equals(restaurantName)).findAny().isEmpty()) {
            System.out.println("Wrong name or restaurant not exist");
            System.out.println();
            throw new IllegalStateException();
        }*/
        if (restaurantsList.stream().noneMatch(r -> r.getName().equals(restaurantName))) {
            System.out.println("Wrong name or restaurant not exist");
            System.out.println();
            return;
            //throw new IllegalStateException();
        }
        final var restaurantToDelete = restaurantsList.stream()
                .filter((Restaurant r) -> r.getName().equals(restaurantName))
                .findAny().get();
        restaurantsList.remove(restaurantToDelete);


        /*var isRestaurantFound = false;
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                isRestaurantFound = true;
            }
        }
        if (isRestaurantFound == false) {
            System.out.println("Wrong name or restaurant not exist");
            System.out.println();
            throw new IllegalStateException();
        }
        for (Restaurant restaurant : restaurantsList) {
            if (restaurant.getRestaurantName().equals(restaurantName)) {
                restaurantsList.remove(restaurant);
                System.out.println("Restaurant deleted");
                System.out.println();
            }
        }*/
    }

    private Restaurant removeRestaurant(Set<Restaurant> restaurantsList, Restaurant restaurant) {
        restaurantsList.remove(restaurant);
        System.out.println("Restaurant deleted");
        return restaurant;
    }

    public Restaurant add(String name, String address, RestaurantType restaurantType) {
        var restaurant = new Restaurant(name, address, restaurantType);
        restaurantRepository.add(restaurant);
        return restaurant;
    }

    public void editRestaurantAddressByName() {
        System.out.print("Type restaurant name: ");
        final var restaurantName = restaurantInputValidator.readLine();
        System.out.print("Type new restaurant address: ");
        final var newAddress = restaurantInputValidator.readLine();
        restaurantRepository.updateRestaurantAddressByName(restaurantName, newAddress);
        System.out.println("Updated restaurant address");
    }
}

// Zmienic var na final var