package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.service.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping()
    public List<FoodItem> getAllFood() {
        return foodService.getAllFood();
    }

    /*@GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable("id") UUID id) {
        return foodService.getFoodItemById(id);
    }*/

}
