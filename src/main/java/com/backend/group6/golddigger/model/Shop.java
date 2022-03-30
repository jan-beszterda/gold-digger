package com.backend.group6.golddigger.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopId;
    private String shopName;
    @OneToMany(mappedBy = "shop", fetch = FetchType.EAGER)
    private List<InventoryItem> shopInventory;

    public Shop() {
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<InventoryItem> getShopInventory() {
        return shopInventory;
    }

    public void setShopInventory(List<InventoryItem> shopInventory) {
        this.shopInventory = shopInventory;
    }
}
