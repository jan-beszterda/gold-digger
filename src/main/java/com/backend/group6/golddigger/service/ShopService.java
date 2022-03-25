package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ShopInventoryDAO;
import com.backend.group6.golddigger.model.InventoryItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopService {
    ShopInventoryDAO shopInventoryDAO;

    public ShopService(ShopInventoryDAO shopInventoryDAO) {
        this.shopInventoryDAO = shopInventoryDAO;
    }

    public List<InventoryItem> getInventory() {
        return shopInventoryDAO.getInventory();
    }

    public void removeItem(UUID id, int count) {
        shopInventoryDAO.removeItem(id, count);
    }
}
