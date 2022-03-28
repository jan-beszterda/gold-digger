package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BackpackService {

    BackpackDAO backpackDAO;

    public BackpackService(BackpackDAO backpackDAO) {
        this.backpackDAO = backpackDAO;
    }

    public List<FoodItem> getFoodFromBackpack() {
        return backpackDAO.getFoodFromBackpack();
    }

    public FoodItem getFoodItemByID(UUID id) {
        return backpackDAO.findFoodItemById(id).orElse(null);
    }

    public void putFoodItemInBackpack(FoodItem foodItemToPutIn) {
        backpackDAO.createFoodItem(foodItemToPutIn);
    }

    public void removeFoodFromBackpack(UUID id) {
        backpackDAO.deleteFoodItem(id);
    }


}