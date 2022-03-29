package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getAllPlayers(@PathParam("id") Integer id) {
        if (id != null) {
            return List.of(playerService.getPlayerById(id));

        }
        return playerService.getAllPlayers();
    }

    @PostMapping()
    public void addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
    }
}
