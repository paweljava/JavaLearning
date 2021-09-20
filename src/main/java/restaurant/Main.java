package restaurant;

import java.util.Scanner;

public class Main {
    static void console () {
        System.out.println("If you want to exit, type \"exit\" ");
        System.out.println("If you want to create a restaurant, type \"1\"");

        String restaurantName = "", restaurantAddress = "", restaurantType = "", choose;
        String[][] restaurantList = {{restaurantName, restaurantAddress, restaurantType},{}};
        Scanner read = new Scanner(System.in);
        choose = read.next();


        switch (choose) {
            case "exit" -> System.out.println("Bye bye");
            case "1" -> {
                System.out.println("Type restaurant name");
                restaurantList.restaurantName = read.next();
                System.out.println("Type restaurant address");
                restaurantAddress = read.next();
                System.out.println("Type restaurant type");
                restaurantType = read.next();
                // Validation


            }
            case "2" -> System.out.println("");
            case "3" -> System.out.println("");
            case "4" -> System.out.println("");
        }
    }


    public static void main(String[] args) {
        console();
    }
}

