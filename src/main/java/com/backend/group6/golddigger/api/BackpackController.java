package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.service.BackpackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backpack")
public class BackpackController {

    BackpackService backpackService;

    public BackpackController(BackpackService backpackService) {
        this.backpackService = backpackService;
    }

    @GetMapping()
    public List<FoodItem> getFoodFromBackpack() {
        return backpackService.getFoodFromBackpack();
    }

    @PostMapping()
    public void putFoodInBackpack(@RequestBody FoodItem foodItemToPutIn) {
        backpackService.putFoodItemInBackpack(foodItemToPutIn);
    }
}
