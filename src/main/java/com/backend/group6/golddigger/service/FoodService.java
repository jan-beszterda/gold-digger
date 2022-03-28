package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.FoodDAO;
import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FoodService {

    FoodDAO foodDAO;

    public FoodService(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public List<FoodItem> getAllFood() {
        return foodDAO.getAllFoodItems();
    }

    public FoodItem getFoodItemById(UUID id) {
        return foodDAO.getFoodItemById(id).orElse(null);
    }
}
