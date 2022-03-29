package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "mines")
public class Mine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double totalGold;
    private double difficulty;

    public Mine() {
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
