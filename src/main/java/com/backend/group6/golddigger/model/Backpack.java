package com.backend.group6.golddigger.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "backpacks")
public class Backpack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer backpackId;
    private double maxWeight;
    @OneToMany(mappedBy = "backpack", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "backpackId")
    @JsonManagedReference
    private List<FoodItem> foodItems;

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
}


