package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.repository.MineRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MineDAO {
    MineRepository repository;

    public MineDAO(MineRepository repository) {
        this.repository = repository;
    }

    public Iterable<Mine> getAllMines() {
        return repository.findAll();
    }

    public Optional<Mine> getMineByID(Integer id) {
        return repository.findById(id);
    }

    public void deleteMine(Integer id) {
        repository.deleteById(id);
    }

    public void createRandomMine(Mine mineToCreate) {
        repository.save(mineToCreate);
    }
}
