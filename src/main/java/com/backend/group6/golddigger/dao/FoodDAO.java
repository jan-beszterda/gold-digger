package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.repository.FoodRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FoodDAO {

    FoodRepository repository;

    public FoodDAO(FoodRepository repository) {
        this.repository = repository;
    }

    public List<FoodItem> DB = new ArrayList<>(List.of(
            new FoodItem(1, "Bread", 0.2, 0.2),
            new FoodItem(2, "Meat", 0.5, 0.3),
            new FoodItem(3, "Water", 0.1, 0.1),
            new FoodItem(4, "Vegetables", 0.3, 0.2),
            new FoodItem(5, "Wine", 0.4, 0.6)));

    public Iterable<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    public Optional<FoodItem> findFoodItemById(Integer id) {
        return repository.findById(id);
    }

    public void saveFoodItem(FoodItem foodItem) {
        repository.save(foodItem);
    }

}
