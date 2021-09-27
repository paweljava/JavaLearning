package com.restaurant.model;

public class Meal {

    private String name;
    private double price;

    public Meal (String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName(){return name;}
    public void setName(String name) {this.name = name;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public void displayMeal() {
        System.out.print(name + " ");
        System.out.print(price + " ");
    };
}
