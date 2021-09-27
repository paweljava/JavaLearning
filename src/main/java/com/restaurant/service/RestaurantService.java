package com.restaurant.service;

import com.restaurant.model.Meal;
import com.restaurant.model.Restaurant;
import com.restaurant.model.RestaurantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

    private List<Restaurant> listOfRestaurants = new ArrayList<>();
    private List<Meal> listOfMeals = new ArrayList<>();

    public void process() {
        Scanner read = new Scanner(System.in);
        do {
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
        } while(true);
    }

    public void exit() {
        System.out.println("Bye bye");
        System.exit(0);
    }

    public void addRestaurant(Scanner read) {
        RestaurantType [] restaurantTypes = RestaurantType.values();

        System.out.print("Type restaurant name: ");
        var name = read.nextLine();
        System.out.print("Type restaurant address: ");
        var address = read.nextLine();
        System.out.print("Type restaurant type. Available types: ");
        for (RestaurantType k : restaurantTypes) {
            System.out.print(k.name() + " ");
        }
        // Validation
        // var type = RestaurantType.valueOf(toUpperCase(read.nextLine()));
        var type = RestaurantType.valueOf(read.nextLine());
        //public boolean validation (String ) {
        //    return validation;
        //}

        // Creation
        var restaurant = new Restaurant(name, address, type);
        listOfRestaurants.add(restaurant);
        restaurant.hashCode();
        // Resataurant information

        System.out.println("Restaurant created:");
        System.out.println("Restaurant id: " + listOfRestaurants.size());
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Type: " + type);
    }

    public void addMeal(Scanner read) {
        System.out.print("Type meal name: ");
        var name = read.nextLine();
        System.out.print("Type meal price: ");
        var price = read.nextDouble();
        System.out.print("Type restaurant id to add the meal to particular restaurant: ");
        var id = Integer.valueOf(read.nextLine());
    }

    public void showRestaurants () {
        for (Restaurant restaurant : listOfRestaurants) {
            restaurant.displayRestaurant();
        }
    }

    public void showMeals(Scanner read) {
        System.out.print("Type restaurant id: ");
        var id = Integer.valueOf(read.nextLine());
        System.out.println("List of meals: ");
    }
}
