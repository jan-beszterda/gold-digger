package com.backend.group6.golddigger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "backpackId")
    private List<FoodItem> foodItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "playerId")
    @JsonIgnore
    private Player player;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}


