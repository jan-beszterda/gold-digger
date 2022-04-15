package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemDAO {
    private ItemRepository repository;

    public ItemDAO(ItemRepository repository) {
        this.repository = repository;
    }

    public Iterable<Item> findAllItems() {
        return repository.findAll();
    }

    public Optional<Item> findItemById(Integer id) {
        return repository.findById(id);
    }

    public Item saveItem(Item item) {
        return repository.save(item);
    }
}
