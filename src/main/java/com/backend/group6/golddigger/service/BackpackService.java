package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackpackService {

    BackpackDAO backpackDAO;
    FoodService foodService;

    public BackpackService(BackpackDAO backpackDAO, FoodService foodService) {
        this.backpackDAO = backpackDAO;
        this.foodService = foodService;
    }

    public List<Backpack> getAllBackpacks() {
        return (List<Backpack>) backpackDAO.getAllBackpacks();
    }

    public Backpack getBackpackById(Integer id) {
        return backpackDAO.getBackpackById(id).orElse(null);
    }

    public void addItemToBackpack(FoodItem item) {
        foodService.addFoodItem(item);
    }
}
