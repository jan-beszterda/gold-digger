package com.backend.group6.golddigger.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fooditems")
public class FoodItem{

    private Integer id;
    private String name;
    private double healthEffect;
    private double weight;

    public FoodItem(Integer id, String name, double healthEffect, double weight) {
        this.id = id;
        this.name = name;
        this.healthEffect = healthEffect;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
