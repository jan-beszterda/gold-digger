package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.repository.BackpackRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BackpackDAO {
    private BackpackRepository repository;

    public BackpackDAO(BackpackRepository repository) {
        this.repository = repository;
    }

    public Iterable<Backpack> findAllBackpacks() {
        return repository.findAll();
    }

    public Optional<Backpack> findBackpackById(Integer id) {
        return repository.findById(id);
    }

    public Backpack saveBackpack(Backpack backpack) {
        return repository.save(backpack);
    }
}
