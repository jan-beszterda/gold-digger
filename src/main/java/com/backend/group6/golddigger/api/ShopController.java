package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Shop;
import com.backend.group6.golddigger.service.ShopService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {
    ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    /*@GetMapping()
    public Shop getShopById(@PathParam("id") Integer id) {
        return shopService.getShopById(id);
    }*/
}
