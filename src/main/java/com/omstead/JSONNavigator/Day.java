package com.omstead.JSONNavigator;

public class Day {
    private double sleep;
    private double sugar;
    private double phone;
    private double fruit;
    private double vegetables;
    private double caffeine;
    private double strength;
    private double moderate;
    private double high;
    private double steps;

    //Getters and setters
    public double getPhone() {
        return phone;
    }
    public void setPhone(double phone) {
        this.phone = phone;
    }
    public double getSleep() {
        return sleep;
    }
    public void setSleep(double sleep) {
        this.sleep = sleep;
    }
    public double getSugar() {
        return sugar;
    }
    public void setSugar(double sugar) {
        this.sugar = sugar;
    }
    public double getFruit() {
        return fruit;
    }
    public void setFruit(double fruit) {
        this.fruit = fruit;
    }
    public double getVegetables() {
        return vegetables;
    }
    public void setVegetables(double vegetables) {
        this.vegetables = vegetables;
    }
    public double getCaffeine() {
        return caffeine;
    }
    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }
    public double getStrength() {
        return strength;
    }
    public void setStrength(double strength) {
        this.strength = strength;
    }
    public double getModerate() {
        return moderate;
    }
    public void setModerate(double moderate) {
        this.moderate = moderate;
    }
    public double getHigh() {
        return high;
    }
    public void setHigh(double high) {
        this.high = high;
    }
    public double getSteps() {
        return steps;
    }
    public void setSteps(double steps) {
        this.steps = steps;
    }
    //Returns an array of all of this day's stats
    public double[] getAll() {
        return new double[]{this.sleep, this.sugar, this.phone, this.fruit, this.vegetables, this.caffeine, this.strength, this.moderate, this.high, this.steps};
    }
    //Returns a String array of the header things for displaying avgs, students. etc.
    public static String[] getHeader() {
        return new String[] {"Sleep:", "Sugar:", "Phone:", "Fruit:", "Veggies:", "Caffeine:", "Strength:", "Moderate:", "High:", "Steps:"};
    }
    //For matching categories to their array index
    public static String[] getCategories() {
        return new String[] {"sleep", "sugar", "phone", "fruit", "vegetables", "caffeine", "strength", "moderate", "high", "steps"};
    }
}

