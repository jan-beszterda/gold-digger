package com.backend.group6.golddigger.model;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double goldAmount;
    private double health;
    private static int maxActions;
    private int actionsRemaining;
    @ManyToOne
    @JoinColumn(name = "id")
    private Mine currentMine;
    @OneToOne
    @JoinColumn(name = "id")
    private Backpack backpack;
    @OneToOne
    @JoinColumn(name = "id")
    private Pickaxe pickaxe;

    public Player() {
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

    public static int getMaxActions() {
        return maxActions;
    }

    public static void setMaxActions(int maxActions) {
        Player.maxActions = maxActions;
    }

    public int getActionsRemaining() {
        return actionsRemaining;
    }

    public void setActionsRemaining(int actionsRemaining) {
        this.actionsRemaining = actionsRemaining;
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

    public Pickaxe getPickaxe() {
        return pickaxe;
    }

    public void setPickaxe(Pickaxe pickaxe) {
        this.pickaxe = pickaxe;
    }
}
