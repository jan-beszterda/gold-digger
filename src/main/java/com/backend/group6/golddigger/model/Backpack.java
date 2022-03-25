package com.backend.group6.golddigger.model;

import java.util.ArrayList;

public class Backpack {

    private double maxWeight;
    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    public Backpack(double maxWeight, ArrayList<FoodItem> foodItems) {
        this.maxWeight = maxWeight;
        this.foodItems = foodItems;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }
}


