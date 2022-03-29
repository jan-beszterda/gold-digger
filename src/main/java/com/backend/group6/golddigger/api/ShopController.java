package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.service.ShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop")
public class ShopController {
    ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


}
