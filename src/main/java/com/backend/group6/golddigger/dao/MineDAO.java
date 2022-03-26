package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Mine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MineDAO {

    public List<Mine> DB = new ArrayList<>(List.of(
            new Mine(UUID.randomUUID(), "Dark mine", 250, 0.5),
            new Mine(UUID.randomUUID(), "Light mine", 500, 0.8)));

    public List<Mine> getAllMines() {
        return DB;
    }

    public Optional<Mine> getMineByID(UUID id) {
        return getAllMines().stream()
                .filter(mine -> mine.getId().equals(id))
                .findFirst();
    }

    public void deleteMine(UUID id) {
        Optional<Mine> mineToRemove = getMineByID(id);
        DB.remove(mineToRemove.get());
    }
}
