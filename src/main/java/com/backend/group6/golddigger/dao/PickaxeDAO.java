package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.repository.PickaxeRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PickaxeDAO {
    private PickaxeRepository repository;

    public PickaxeDAO(PickaxeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Pickaxe> findAllPickaxes() {
        return repository.findAll();
    }

    public Optional<Pickaxe> findPickaxeById(Integer id) {
        return repository.findById(id);
    }

    public Pickaxe savePickaxe(Pickaxe pickaxe) {
        return repository.save(pickaxe);
    }
}
