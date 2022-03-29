package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pickaxes")
public class Pickaxe extends Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private double strength;
    private double condition;

    public Pickaxe() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
