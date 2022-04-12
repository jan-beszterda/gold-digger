package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.repository.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ItemDAO {
    ItemRepository repository;

    public ItemDAO(ItemRepository repository) {
        this.repository = repository;
    }

    public Iterable<Item> getAllItems() {
        return repository.findAll();
    }

    public Optional<Item> getItemById(Integer id) {
        return repository.findById(id);
    }

    public Item addItem(Item item) {
        return repository.save(item);
    }
}
