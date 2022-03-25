package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.InventoryItem;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ShopInventoryDAO {
    private static List<InventoryItem> data = new ArrayList<>(List.of(
            new InventoryItem(new FoodItem(UUID.randomUUID(), "Apple", 0.2, 0.3), 20.0, 100),
            new InventoryItem(new FoodItem(UUID.randomUUID(), "Bread", 0.4, 0.5), 30.0, 20),
            new InventoryItem(new Pickaxe(UUID.randomUUID(), "Wooden pickaxe", 1.0, 100.0), 50.0, 10)
    ));

    public List<InventoryItem> getInventory() {
        return data;
    }
}
