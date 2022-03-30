package com.backend.group6.golddigger.model;

import javax.persistence.*;

@Entity
@Table(name = "inventoryItems")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryItemId;
    @OneToOne
    @JoinColumn(name = "itemId")
    private Item inventoryItem;
    private double itemPrice;
    private int itemCount;
    @ManyToOne
    @JoinColumn(name = "shopId")
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public InventoryItem() {
    }

    public Integer getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(Integer inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public Item getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(Item inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
