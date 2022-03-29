package com.backend.group6.golddigger.model;

import javax.persistence.*;

@Entity
@Table(name="foodItems")
public class FoodItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double healthEffect;
    private double weight;

    public FoodItem() {
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
