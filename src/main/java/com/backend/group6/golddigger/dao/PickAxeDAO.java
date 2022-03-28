package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PickAxeDAO {

    public List<Pickaxe> DB = new ArrayList<>(List.of(
            new Pickaxe(UUID.randomUUID(), "Diamond pickaxe", 1, 1),
            new Pickaxe(UUID.randomUUID(), "Iron pickaxe", 0.7, 1),
            new Pickaxe(UUID.randomUUID(), "Aluminium pickaxe", 0.3, 1),
            new Pickaxe(UUID.randomUUID(), "Plastic pickaxe", 0.1, 1)));


    public List<Pickaxe> getAllPickaxes() {
        return DB;
    }

    public Optional<Pickaxe> getPickaxeById(UUID id) {
        return getAllPickaxes().stream()
                .filter(pickaxe -> pickaxe.getId().equals(id))
                .findFirst();
    }
}
