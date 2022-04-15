package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/available")
    public List<Player> getAllAvailablePlayers() {
        return playerService.getAllAvailablePlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/create")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.createNewPlayer(player);
    }

    @PutMapping("/{playerId}/dig")
    public Player dig(@PathVariable("playerId") Integer playerId) {
        return playerService.dig(playerId);
    }

    @PutMapping("/{playerId}/eat/{itemId}")
    public Player eat(@PathVariable("playerId") Integer playerId, @PathVariable("itemId") Integer itemId) {
        return playerService.eat(playerId, itemId);
    }

    @PutMapping("/{playerId}/buyItem/{itemId}")
    public Player buyItem(@PathVariable("playerId") Integer playerId, @PathVariable("itemId") Integer itemId) {
        return playerService.buyItem(playerId, itemId);
    }

    @PutMapping("/{playerId}/move")
    public Player move(@PathVariable("playerId") Integer playerId) {
        return playerService.move(playerId);
    }

    @PutMapping("/{playerId}/sleep")
    public Player sleep(@PathVariable("playerId") Integer playerId) {
        return playerService.sleep(playerId);
    }
}
