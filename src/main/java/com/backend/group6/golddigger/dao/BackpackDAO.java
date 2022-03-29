package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.repository.BackpackRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BackpackDAO {
    BackpackRepository repository;

    public BackpackDAO(BackpackRepository repository) {
        this.repository = repository;
    }

    public Iterable<Backpack> getAllBackpacks() {
        return repository.findAll();
    }

    public Optional<Backpack> getBackpackById(Integer id) {
        return repository.findById(id);
    }
}
