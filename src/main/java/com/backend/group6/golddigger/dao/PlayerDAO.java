package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.repository.PlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlayerDAO {
    PlayerRepository repository;

    public PlayerDAO(PlayerRepository repository) {
        this.repository = repository;
    }

    public Iterable<Player> findAllPlayers() {
        return repository.findAll();
    }

    public Optional<Player> findPlayerById(Integer id) {
        return repository.findById(id);
    }

    public void addPlayer(Player player) {
        repository.save(player);
    }
}
