package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackpackService {

    BackpackDAO backpackDAO;

    public BackpackService(BackpackDAO backpackDAO) {
        this.backpackDAO = backpackDAO;
    }

    public List<FoodItem> getFoodFromBackpack() {
        return backpackDAO.getFoodFromBackpack();
    }

    public void putFoodItemInBackpack(FoodItem foodItemToPutIn) {
        backpackDAO.createFoodItem(foodItemToPutIn);
    }
}
