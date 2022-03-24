package com.backend.group6.golddigger.model;

import java.util.UUID;

public class Player {

    private UUID id;
    private String name;
    private double goldAmount;
    private double health;
    private Mine currentMine;
    private Backpack backpack;

    public Player(UUID id, String name, double goldAmount, double health, Mine currentMine, Backpack backpack) {
        this.id = id;
        this.name = name;
        this.goldAmount = goldAmount;
        this.health = health;
        this.currentMine = currentMine;
        this.backpack = backpack;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(double goldAmount) {
        this.goldAmount = goldAmount;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Mine getCurrentMine() {
        return currentMine;
    }

    public void setCurrentMine(Mine currentMine) {
        this.currentMine = currentMine;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }
}