package com.restaurant.service;

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
    private List<Restaurant> list = new ArrayList<>();

    public void process() {
        Scanner read = new Scanner(System.in);
        do {
            System.out.println("If you want to exit, type \"exit\" ");
            System.out.println("If you want to create a restaurant, type \"1\"");
            String choose = read.nextLine();

            switch (choose) {
                case "exit" -> exit();
                case "1" -> addRestaurant(read);
                case "2" -> System.out.println("");
                case "3" -> System.out.println("");
                case "4" -> System.out.println("");
            }

        } while (true);
    }

    private void exit() {
        System.out.println("Bye bye");
        System.exit(1);
    }

    private void addRestaurant(Scanner read) {
        System.out.println("Type restaurant name");
        var name = read.nextLine();
        System.out.println("Type restaurant address");
        var address = read.nextLine();
        System.out.println("Type restaurant type");
        // Validation
        var type = type(read);


        // Creation
        var restaurant = new Restaurant(name, address, type);
        list.add(restaurant);
        System.out.println("Restaurant created");

    }

    private RestaurantType type(Scanner read) {
        var type = read.nextLine();
        return RestaurantType.valueOf(type.toUpperCase());
    }
}
