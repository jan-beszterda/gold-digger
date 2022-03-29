package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PlayerDAO;
import com.backend.group6.golddigger.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) playerDAO.findAllPlayers();
    }

    public Player getPlayerById(Integer id) {
        return playerDAO.findPlayerById(id).orElse(null);
    }

    public void addPlayer(Player player) {
        playerDAO.addPlayer(player);
    }
}
