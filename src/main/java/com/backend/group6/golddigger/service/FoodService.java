package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.FoodDAO;
import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    FoodDAO foodDAO;

    public FoodService(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public List<FoodItem> getAllFood() {
        return (List<FoodItem>) foodDAO.getAllFoodItems();
    }

    public FoodItem getFoodItemById(Integer id) {
        return foodDAO.findFoodItemById(id).orElse(null);
    }

    public void addFoodItem(FoodItem foodItem) {
        foodDAO.saveFoodItem(foodItem);
    }
}
