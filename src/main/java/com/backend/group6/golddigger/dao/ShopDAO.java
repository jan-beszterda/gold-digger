package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Shop;
import com.backend.group6.golddigger.repository.ShopRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShopDAO {
    ShopRepository repository;

    public ShopDAO(ShopRepository repository) {
        this.repository = repository;
    }

    public Iterable<Shop> getAllShops() {
        return repository.findAll();
    }

    public Optional<Shop> getShopById(Integer id) {
        return repository.findById(id);
    }
}
