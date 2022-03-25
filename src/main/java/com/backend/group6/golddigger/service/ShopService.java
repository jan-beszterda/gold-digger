package com.backend.group6.golddigger.service;

import org.springframework.stereotype.Service;

@Service
public class ShopService {
    ShopInventoryDAO shopInventoryDAO;

    public ShopService(ShopInventoryDAO shopInventoryDAO) {
        this.shopInventoryDAO = shopInventoryDAO;
    }

    public List<InventoryItem> getInventory() {
        return shopInventoryDAO.getIventory();
    }
}
