package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.InventoryItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<InventoryItem> getInventory() {
        return shopService.getInventory();
    }
}
