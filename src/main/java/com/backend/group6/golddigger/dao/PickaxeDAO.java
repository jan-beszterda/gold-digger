package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.repository.PickaxeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PickaxeDAO {
    PickaxeRepository repository;

    public PickaxeDAO(PickaxeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Pickaxe> getAllPickaxes() {
        return repository.findAll();
    }

    public Optional<Pickaxe> getPickaxeById(Integer id) {
        return repository.findById(id);
    }

    public void addPickaxe(Pickaxe pickaxe) {
        repository.save(pickaxe);
    }
}
