package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ShopDAO;
import com.backend.group6.golddigger.model.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    private ShopDAO shopDAO;

    public ShopService(ShopDAO shopDAO) {
        this.shopDAO = shopDAO;
    }

    public List<Shop> getAllShops() {
        return (List<Shop>) shopDAO.findAllShops();
    }

    public Shop getShopById(Integer id) {
        return shopDAO.findShopById(id).orElse(null);
    }

    public Shop addShop(Shop shop) {
        return shopDAO.saveShop(shop);
    }
}
