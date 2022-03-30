package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping()
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }

    @PostMapping("/buyFood")
    public void buyFood(@RequestBody FoodItem item) {
        playerService.addItem(item);
    }
}
