package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PlayerDAO;
import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.model.FoodItem;
import com.backend.group6.golddigger.model.Player;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlayerService {
    PlayerDAO playerDAO;
    BackpackService backpackService;
    Player player;

    public PlayerService(PlayerDAO playerDAO, BackpackService backpackService) {
        this.playerDAO = playerDAO;
        this.backpackService = backpackService;
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


    public void addItem(Integer id, FoodItem item) {
        Player player = getPlayerById(id);
        Backpack backpack = player.getBackpack();
        item.setBackpack(backpack);
        backpack.getFoodItems().add(item);
        //backpackService.saveBackpack(backpack);
        //player.setBackpack(backpack);
        playerDAO.addPlayer(player);
    }


    public void dig() {
        goldDug();
        increaseGoldAmount();
        decreaseHealth();
        decreasePickaxeCondition();
        decreaseAmountGoldInMine();
    }

    public double hitWithPickaxe() {
        Random random = new Random();
        double randomHit = Math.round(random.nextDouble(0.0, 1.0) * 10.0) / 10.0;
        double totalHit = randomHit * (player.getHealth() / 100)
                * player.getPickaxe().getStrength()
                * (player.getPickaxe().getCondition() / 100);
        return totalHit;
    }

    public double goldDug() {
        return player.getCurrentMine().getTotalGold() * hitWithPickaxe()
                * (1 - player.getCurrentMine().getDifficulty());
    }

    public void increaseGoldAmount() {
        Double newAmount = player.getGoldAmount() + goldDug();
        player.setGoldAmount(newAmount);
    }

    public void decreaseHealth() {
        Random random = new Random();
        Double newHealth = player.getHealth() - (player.getHealth() * hitWithPickaxe()
                * player.getCurrentMine().getDifficulty()
                * random.nextDouble(0, 100) * 10.0 / 10.0);
        if (newHealth <= 0) {
            die();
        }
        player.setHealth(newHealth);
    }

    public void die() {

    }

    public void decreasePickaxeCondition() {
        Random random = new Random();
        Double newCondition = player.getPickaxe().getCondition() - (player.getPickaxe().getCondition()
                * hitWithPickaxe() * player.getCurrentMine().getDifficulty()
                * random.nextDouble(0, 100) * 10.0 / 10.0);
        if (newCondition <= 0) {
            wastePickaxe();
        }
        player.getPickaxe().setCondition(newCondition);
    }

    public void wastePickaxe() {
        player.setPickaxe(null);
    }

    public void decreaseAmountGoldInMine() {
        Double newTotalGoldInMine = player.getCurrentMine().getTotalGold() - goldDug();
        if (newTotalGoldInMine <= 0) {
            closeMine();
        }
        player.getCurrentMine().setTotalGold(newTotalGoldInMine);
    }

    public void closeMine() {
        player.setCurrentMine(null);
    }
}
