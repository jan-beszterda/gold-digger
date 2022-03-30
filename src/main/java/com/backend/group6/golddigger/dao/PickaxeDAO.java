package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.repository.PickaxeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PickaxeDAO {

    /*public List<Pickaxe> DB = new ArrayList<>(List.of(
            new Pickaxe(UUID.randomUUID(), "Diamond pickaxe", 1, 1),
            new Pickaxe(UUID.randomUUID(), "Iron pickaxe", 0.7, 1),
            new Pickaxe(UUID.randomUUID(), "Aluminium pickaxe", 0.3, 1),
            new Pickaxe(UUID.randomUUID(), "Plastic pickaxe", 0.1, 1)));*/
    PickaxeRepository repository;

    public PickaxeDAO(PickaxeRepository repository) {
        this.repository = repository;
    }

    public Iterable<Pickaxe> getAllPickaxes() {
        return repository.findAll();
    }

    /*public Optional<Pickaxe> getPickaxeById(UUID id) {
        return getAllPickaxes().stream()
                .filter(pickaxe -> pickaxe.getId().equals(id))
                .findFirst();
    }*/
}
