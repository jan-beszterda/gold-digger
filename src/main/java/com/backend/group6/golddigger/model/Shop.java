package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany
    @JoinTable(name = "inventory_items")
    private ArrayList<InventoryItem> inventory;

    public Shop() {
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

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<InventoryItem> inventory) {
        this.inventory = inventory;
    }
}
