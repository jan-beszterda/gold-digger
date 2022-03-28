package com.backend.group6.golddigger.dao;

import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.model.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PlayerDAO {
    private static List<Player> data = new ArrayList<>(List.of(
            new Player(UUID.randomUUID(), "New player", 100.0,100.0, new Mine(UUID.randomUUID(),
                    "New mine", 1000.0, 0.2), new Backpack(15.0,
                    new ArrayList<>(List.of(new FoodItem(UUID.randomUUID(), "Apple", 2.0, 0.3)))))
    ));

    public List<Player> getPlayers() {
        return data;
    }

    public Optional<Player> getPlayerById(UUID id) {
        return getPlayers()
                .stream()
                .filter(player -> player.getId().equals(id))
                .findFirst();
    }

    public void addNewPlayer(Player newPlayer) {
        UUID id = UUID.randomUUID();
        insertPlayer(id, newPlayer);
    }

    private void insertPlayer(UUID id, Player newPlayer) {
        data.add(new Player(id, newPlayer.getName(), 100.0, 100.0, new Mine(UUID.randomUUID(),
                "New mine", 1000.0, 0.2),
                new Backpack(15.0, new ArrayList<>(List.of(
                        new FoodItem(UUID.randomUUID(), "Apple", 2.0, 0.2))))));
    }
}
