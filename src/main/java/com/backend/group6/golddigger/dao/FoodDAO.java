package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.repository.FoodRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class FoodDAO {
    /*public List<FoodItem> DB = new ArrayList<>(List.of(
            new FoodItem(UUID.randomUUID(), "Bread", 0.2, 0.2),
            new FoodItem(UUID.randomUUID(), "Meat", 0.5, 0.3),
            new FoodItem(UUID.randomUUID(), "Water", 0.1, 0.1),
            new FoodItem(UUID.randomUUID(), "Vegetables", 0.3, 0.2),
            new FoodItem(UUID.randomUUID(), "Wine", 0.4, 0.6)));

    public List<FoodItem> getAllFoodItems() {
        return DB;
    }

    public Optional<FoodItem> getFoodItemById(UUID id) {
        return getAllFoodItems().stream()
                .filter(pickaxe -> pickaxe.getId().equals(id))
                .findFirst();
    }*/

    FoodRepository repository;

    public FoodDAO(FoodRepository repository) {
        this.repository = repository;
    }

    public Iterable<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    public void addFoodItem(FoodItem item) {
        repository.save(item);
    }
}
