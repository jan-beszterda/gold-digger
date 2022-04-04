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
        //item.setBackpack(backpack);
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

    public List<FoodItem> showFoodInBackpack() {
        return player.getBackpack().getFoodItems();
    }

    public FoodItem chooseFoodItemInBackpack(Integer id) {
        return showFoodInBackpack().stream()
                .filter(foodItem -> foodItem.getItemId().equals(id))
                .findFirst().orElse(null);
    }

    public double foodItemsMaxHealthIncrement(FoodItem foodItem) {
        return foodItem.getHealthEffect() * foodItem.getWeight() / 100;
    }

    public double healthNeededToFull() {
        return 100 - player.getHealth();
    }

    public void eat(FoodItem foodItem) {
        double foodWeightNeeded = 100 * healthNeededToFull() / foodItem.getHealthEffect();
        if (foodWeightNeeded > foodItem.getWeight()) {
            double newHealth = player.getHealth() + foodItemsMaxHealthIncrement(foodItem);
            player.setHealth(newHealth);
            player.getBackpack().removeFoodItem(foodItem);
        } else if (foodWeightNeeded <= foodItem.getWeight()) {
            double newFoodWeight = foodItem.getWeight()
                    - (100 * (foodItemsMaxHealthIncrement(foodItem) - healthNeededToFull())
                    / foodItem.getHealthEffect());
            player.setHealth(100);
            foodItem.setWeight(newFoodWeight);
        }
    }

    public void sleep() {
        int numberOfActions = decreaseActionsRemainingByOne();
        player.setActionsRemaining(numberOfActions);
        double newHealthAfterSleeping = increaseHealthBySleeping();
        player.setHealth(newHealthAfterSleeping);
    }

    public int decreaseActionsRemainingByOne() {
        int decreasedNumberOfActions = player.getActionsRemaining() - 1;
        if (decreasedNumberOfActions == 0) {
            die();
        }
        return decreasedNumberOfActions;
    }

    public double increaseHealthBySleeping() {
        return player.getHealth() + 10;
    }
}
