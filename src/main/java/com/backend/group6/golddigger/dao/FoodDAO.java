package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.repository.FoodRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FoodDAO {
    private FoodRepository repository;

    public FoodDAO(FoodRepository repository) {
        this.repository = repository;
    }

    public Iterable<FoodItem> findAllFoodItems() {
        return repository.findAll();
    }

    public Optional<FoodItem> findFoodItemById(Integer id) {
        return repository.findById(id);
    }

    public FoodItem saveFoodItem(FoodItem foodItem) {
        return repository.save(foodItem);
    }
}
