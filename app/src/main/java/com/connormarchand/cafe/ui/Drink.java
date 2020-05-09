package com.connormarchand.cafe.ui;

public class Drink {

    int id;
    String name;
    int coffee;
    int water;
    int milk;
    int favorite;

    public Drink(int id, String name, int coffee, int water, int milk, int favorite) {
        this.id = id;
        this.name = name;
        this.coffee = coffee;
        this.water = water;
        this.milk = milk;
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getCoffeeString(){
        return Integer.toString(coffee);
    }

    public String getWaterString(){
        return Integer.toString(water);
    }

    public String getMilkString(){
        return Integer.toString(milk);
    }

}
