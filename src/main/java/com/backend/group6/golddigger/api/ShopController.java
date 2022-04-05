package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.model.Shop;
import com.backend.group6.golddigger.service.ItemService;
import com.backend.group6.golddigger.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    ShopService shopService;
    ItemService itemService;

    public ShopController(ShopService shopService, ItemService itemService) {
        this.shopService = shopService;
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable("id") Integer id) {
        return shopService.getShopById(id);
    }

    /*@GetMapping("/{id}/inventory")
    public List<Item> getShopInventory(@PathVariable("id") Integer id) {
        return itemService.getAllItems()
                .stream()
                .filter(item -> item.getShop().getShopId().equals(id))
                .collect(Collectors.toList());
    }*/
}
