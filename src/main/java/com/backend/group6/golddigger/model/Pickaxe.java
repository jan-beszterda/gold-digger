package com.backend.group6.golddigger.model;

import javax.persistence.*;

@Entity
@Table(name = "pickaxes")
public class Pickaxe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double strength;
    private double condition;

    public Pickaxe(Integer id, String name, double strength, double condition) {
        this.id = id;
        this.name = name;
        this.strength = strength;
        this.condition = condition;
    }

    public Pickaxe() {

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

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getCondition() {
        return condition;
    }

    public void setCondition(double condition) {
        this.condition = condition;
    }
}
