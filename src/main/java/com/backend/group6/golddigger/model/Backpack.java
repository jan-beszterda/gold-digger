package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "backpacks")
public class Backpack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer backpackId;
    private double maxWeight;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "backpackId")
    private List<FoodItem> foodItems = new ArrayList<>();

    public Backpack() {
    }

    public Integer getBackpackId() {
        return backpackId;
    }

    public void setBackpackId(Integer backpackId) {
        this.backpackId = backpackId;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void removeFoodItem (FoodItem foodItemToRemove) {
        foodItems.remove(foodItemToRemove);
    }
}


