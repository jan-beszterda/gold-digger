package com.backend.group6.golddigger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("Food")
public class FoodItem extends Item{

    private double healthEffect;
    private double weight;
    @ManyToOne
    @JoinColumn(name = "backpack_backpack_id")
    @JsonBackReference
    private Backpack backpack;

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

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }
}
