package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "backpacks")
public class Backpack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double maxWeight;
    private ArrayList<FoodItem> foodItems = new ArrayList<>();

    public Backpack() {
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public ArrayList<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}


