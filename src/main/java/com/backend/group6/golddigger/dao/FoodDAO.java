package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.repository.FoodRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FoodDAO {

    FoodRepository repository;

    public FoodDAO(FoodRepository repository) {
        this.repository = repository;

    public Iterable<FoodItem> getAllFoodItems() {
        return repository.findAll();
    }

    public Optional<FoodItem> getFoodItemById(Integer id) {
        return repository.findById(id);
    }

    public void addFoodItem(FoodItem item) {
        repository.save(item);
    }
}
