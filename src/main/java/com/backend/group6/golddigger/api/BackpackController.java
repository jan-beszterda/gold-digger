package com.backend.group6.golddigger.api;

import  com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.service.BackpackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable("id") UUID id) {
        return backpackService.getFoodItemByID(id);
    }

    @PostMapping()
    public void putFoodInBackpack(@RequestBody FoodItem foodItemToPutIn) {
        backpackService.putFoodItemInBackpack(foodItemToPutIn);
    }

    @DeleteMapping("{id}")
    public void removeFoodFromBackpack(@PathVariable("id") UUID id) {
        backpackService.removeFoodFromBackpack(id);
    }
}
