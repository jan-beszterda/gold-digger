package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.repository.MineRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MineDAO {
    private MineRepository repository;

    public MineDAO(MineRepository repository) {
        this.repository = repository;
    }

    public Iterable<Mine> findAllMines() {
        return repository.findAll();
    }

    public Optional<Mine> findMineByID(Integer id) {
        return repository.findById(id);
    }

    public void deleteMine(Integer id) {
        repository.deleteById(id);
    }

    public Mine saveMine(Mine mine) {
        return repository.save(mine);
    }
}
