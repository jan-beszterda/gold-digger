package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.InventoryItem;
import com.backend.group6.golddigger.service.ShopService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.UUID;

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

    @DeleteMapping("/sell")
    public void removeItem(@PathParam("id") UUID id, @PathParam("count") int count) {
        shopService.removeItem(id, count);
    }
}
