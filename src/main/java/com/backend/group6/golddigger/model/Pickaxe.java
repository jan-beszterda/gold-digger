package com.backend.group6.golddigger.model;

import java.util.UUID;

public class Pickaxe extends Item {

    private double strength;
    private double condition;

    public Pickaxe(UUID id, String name, double strength, double condition) {
        super(id, name);
        this.strength = strength;
        this.condition = condition;
    }

    public double getStrength() {
        return strength;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }
}
