package com.backend.group6.golddigger.model;

import javax.persistence.*;

@Entity
@Table(name = "mines")
public class Mine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mineId;
    private String mineName;
    private double totalGold;
    private double difficulty;

    public Mine() {
    }

    public Integer getMineId() {
        return mineId;
    }

    public void setMineId(Integer mineId) {
        this.mineId = mineId;
    }

    public String getMineName() {
        return mineName;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    public double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(double totalGold) {
        this.totalGold = totalGold;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
}
