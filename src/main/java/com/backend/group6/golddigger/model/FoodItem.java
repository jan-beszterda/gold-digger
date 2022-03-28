package com.backend.group6.golddigger.model;

import java.util.UUID;

public class FoodItem extends Item{

    private double healthEffect;
    private double weight;

    public FoodItem(UUID id, String name, double healthEffect, double weight) {
        super(id, name);
        this.healthEffect = healthEffect;
        this.weight = weight;
    }

    public double getHealthEffect() {
        return healthEffect;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}