package com.backend.group6.golddigger.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @OneToOne
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
