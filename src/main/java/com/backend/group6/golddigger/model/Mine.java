package com.backend.group6.golddigger.model;

import java.util.UUID;

public class Mine {

    private UUID id;
    private String name;
    private double totalGold;
    private double difficulty;

    public Mine(UUID id, String name, double totalGold, double difficulty) {
        this.id = id;
        this.name = name;
        this.totalGold = totalGold;
        this.difficulty = difficulty;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getTotalGold() {
        return totalGold;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setTotalGold(double totalGold) {
        this.totalGold = totalGold;
    }
}
