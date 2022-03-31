package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PlayerDAO;
import com.backend.group6.golddigger.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlayerService {
    PlayerDAO playerDAO;
    BackpackService backpackService;
    MineService mineService;
    PickaxeService pickaxeService;
    ItemService itemService;
    Player player = new Player();

    public PlayerService(PlayerDAO playerDAO, BackpackService backpackService, MineService mineService, PickaxeService pickaxeService, ItemService itemService) {
        this.playerDAO = playerDAO;
        this.backpackService = backpackService;
        this.mineService = mineService;
        this.pickaxeService = pickaxeService;
        this.itemService = itemService;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) playerDAO.findAllPlayers();
    }

    public Player getPlayerById(Integer id) {
        return playerDAO.findPlayerById(id).orElse(null);
    }

    public void addPlayer(Player player) {
        player.setGoldAmount(100.0);
        player.setHealth(100.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        Backpack aBackpack = new Backpack();
        aBackpack.setMaxWeight(15.0);
        /*List<FoodItem> items = itemService.getAllItems()
                .stream()
                .filter(item -> item.)*/
        player.setBackpack(aBackpack);
        Mine aMine = mineService.getAllMines()
                .stream()
                .findAny()
                .get();
        Mine newMine = new Mine();
        newMine.setMineName(aMine.getMineName());
        newMine.setTotalGold(aMine.getTotalGold());
        newMine.setDifficulty(aMine.getDifficulty());
        player.setCurrentMine(newMine);
        Pickaxe aPickaxe = pickaxeService.getAllPickaxes()
                .stream()
                .filter(pickaxe -> pickaxe.getItemName().equalsIgnoreCase("Wooden pickaxe"))
                .findAny()
                .get();
        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemName(aPickaxe.getItemName());
        newPickaxe.setStrength(aPickaxe.getStrength());
        newPickaxe.setCondition(aPickaxe.getCondition());
        player.setPickaxe(newPickaxe);
        playerDAO.addPlayer(player);
    }


    public void addItem(Integer id, FoodItem item) {
        Player player = getPlayerById(id);
        Backpack backpack = player.getBackpack();
        item.setBackpack(backpack);
        backpack.getFoodItems().add(item);
        //backpackService.saveBackpack(backpack);
        //player.setBackpack(backpack);
        playerDAO.addPlayer(player);
    }
      
    public double hitWithPickaxe() {
        Random random = new Random();
        int randomHit = random.nextInt(10);
        double totalHit = randomHit * player.getCurrentMine().getDifficulty()
                * player.getPickaxe().getStrength()
                * player.getPickaxe().getCondition();
        return totalHit;
    }

    public void increaseGoldAmount(double goldToAdd) {
        Double newAmount = player.getGoldAmount() + goldToAdd;
        player.setGoldAmount(newAmount);
    }

    public void decreaseHealth(double healthToLose) {
        Double newHealth = player.getHealth() - healthToLose;
        if (newHealth <= 0) {
            die();
        }
        player.setHealth(newHealth);
    }

    public void die() {

    }

    public void decreasePickaxeCondition(Double conditionToDecrease) {
        Double newCondition = player.getPickaxe().getCondition() - conditionToDecrease;
        if (newCondition <= 0) {
            wastePickaxe();
        }
        player.getPickaxe().setCondition(newCondition);
    }

    public void wastePickaxe() {
        player.setPickaxe(null);
    }

    public void decreaseAmountGoldInMine(double goldToRemove) {
        Double newTotalGoldInMine = player.getCurrentMine().getTotalGold() - goldToRemove;
        if (newTotalGoldInMine <= 0) {
            closeMine();
        }
        player.getCurrentMine().setTotalGold(newTotalGoldInMine);
    }

    public void closeMine() {
        player.setCurrentMine(null);
    }
}
