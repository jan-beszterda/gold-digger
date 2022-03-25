package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class BackpackDAO {
    public List<FoodItem> DB = new ArrayList<>(
            List.of(new FoodItem(UUID.randomUUID(), "Potato", 10, 0.1),
                    new FoodItem(UUID.randomUUID(), "Meet", 50, 0.5)));


    public List<FoodItem> getFoodFromBackpack() {
        return DB;
    }

    public void createFoodItem(FoodItem newFoodItem) {
        UUID id = UUID.randomUUID();
        insertFoodItem(id, newFoodItem);
    }

    private void insertFoodItem(UUID id, FoodItem newFoodItem) {
        DB.add(new FoodItem(id, newFoodItem.getName(), newFoodItem.getHealthEffect(), newFoodItem.getWeight()));
    }
}
