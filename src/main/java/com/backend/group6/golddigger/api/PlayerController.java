package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.model.Player;
import com.backend.group6.golddigger.service.*;
import org.hibernate.query.criteria.internal.ValueHandlerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    PlayerService playerService;
    ShopService shopService;
    FoodService foodService;
    BackpackService backpackService;
    MineService mineService;
    PickaxeService pickaxeService;
    ItemService itemService;

    public PlayerController(PlayerService playerService, ShopService shopService, FoodService foodService,
                            BackpackService backpackService, MineService mineService, PickaxeService pickaxeService, ItemService itemService) {
        this.playerService = playerService;
        this.shopService = shopService;
        this.foodService = foodService;
        this.backpackService = backpackService;
        this.mineService = mineService;
        this.pickaxeService = pickaxeService;
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable("id") Integer id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/create")
    public Player createNewPlayer(@RequestBody Player player) {
        return playerService.createNewPlayer(
                player,
                backpackService.createStartingBackpack(foodService.getStartingItems()),
                mineService.getStartingMine(),
                pickaxeService.getStartingPickaxe()
        );
    }

    @PostMapping("/{playerId}/buyItem/{itemId}")
    public void buyItem(@PathVariable("playerId") Integer playerId, @PathVariable("itemId") Integer itemId) {
        Item item = shopService.sellItem(itemId);
        itemService.addItem(item);
        playerService.buyItem(playerId, item);
    }

    @GetMapping("/{playerId}/dig")
    public void dig(@PathVariable("playerId") Integer playerId) {
        playerService.dig(playerId);
    }

    @GetMapping("/{playerId}/eat/{itemId}")
    public void eat(@PathVariable("playerId") Integer playerId, @PathVariable("itemId") Integer itemId) {
        playerService.eat(playerId, itemId);
    }

    @GetMapping("/{playerId}/sleep")
    public void sleep(@PathVariable("playerId") Integer playerId) {
        playerService.sleep(playerId);
    }

}
