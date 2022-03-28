package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PlayerDAO;
import com.backend.group6.golddigger.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {

    PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

    public Player getPlayerById(UUID id) {
        return playerDAO.getPlayerById(id).orElse(null);
    }

    public void addNewPlayer(Player newPlayer) {
        playerDAO.addNewPlayer(newPlayer);
    }
}
