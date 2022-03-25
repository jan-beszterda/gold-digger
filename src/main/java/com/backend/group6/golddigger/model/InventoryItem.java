package com.backend.group6.golddigger.model;

public class InventoryItem {
    private Item item;
    private double price;
    private int count;

    public InventoryItem(Item item, double price, int count) {
        this.item = item;
        this.price = price;
        this.count = count;
    }

    public Item getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
