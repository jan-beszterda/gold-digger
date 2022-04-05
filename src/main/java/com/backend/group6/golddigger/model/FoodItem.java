package com.backend.group6.golddigger.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Food")
public class FoodItem extends Item{
    private double healthEffect;
    private double weight;

    public FoodItem() {
    }

    public double getHealthEffect() {
        return healthEffect;
    }

    public void setHealthEffect(double healthEffect) {
        this.healthEffect = healthEffect;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
