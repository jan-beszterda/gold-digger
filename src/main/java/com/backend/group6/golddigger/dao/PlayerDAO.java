package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.repository.PlayerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDAO {
    PlayerRepository repository;

    public PlayerDAO(PlayerRepository repository) {
        this.repository = repository;
    }

    public Iterable<Player> findAllPlayers() {
        return repository.findAll();
    }
}
