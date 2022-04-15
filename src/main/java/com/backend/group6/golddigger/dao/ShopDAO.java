package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Shop;
import com.backend.group6.golddigger.repository.ShopRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShopDAO {
    private ShopRepository repository;

    public ShopDAO(ShopRepository repository) {
        this.repository = repository;
    }

    public Iterable<Shop> findAllShops() {
        return repository.findAll();
    }

    public Optional<Shop> findShopById(Integer id) {
        return repository.findById(id);
    }

    public Shop saveShop(Shop shop) {
        return repository.save(shop);
    }
}
