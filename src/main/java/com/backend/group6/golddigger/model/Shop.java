package com.backend.group6.golddigger.model;

import java.util.ArrayList;

public class Shop {

    private String name;
    private ArrayList<InventoryItem> inventory;

    public Shop() {
        this.name = "";
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<InventoryItem> getInventory() {
        return inventory;
    }
}
