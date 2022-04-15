package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Shop;
import com.backend.group6.golddigger.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable("id") Integer id) {
        return shopService.getShopById(id);
    }

    @PostMapping("/create")
    public Shop addShop(@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }
}
