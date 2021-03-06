package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items/food")
public class FoodController {
    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public List<FoodItem> getAllFoodItems() {
        return foodService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable("id") Integer id) {
        return foodService.getFoodItemById(id);
    }

    @PostMapping("/create")
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return foodService.addFoodItem(foodItem);
    }

}
