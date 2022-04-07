package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PlayerDAO;
import com.backend.group6.golddigger.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PlayerService {
    PlayerDAO playerDAO;
    BackpackService backpackService;
    MineService mineService;
    PickaxeService pickaxeService;
    ItemService itemService;
    FoodService foodService;
    ShopService shopService;
    Player player = new Player();

    public PlayerService(PlayerDAO playerDAO, BackpackService backpackService, MineService mineService, PickaxeService pickaxeService,
                         ItemService itemService, ShopService shopService, FoodService foodService) {
        this.playerDAO = playerDAO;
        this.backpackService = backpackService;
        this.mineService = mineService;
        this.pickaxeService = pickaxeService;
        this.itemService = itemService;
        this.shopService = shopService;
        this.foodService = foodService;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) playerDAO.findAllPlayers();
    }

    public Player getPlayerById(Integer id) {
        return playerDAO.findPlayerById(id).orElse(null);
    }

    public Player createNewPlayer(Player player, Backpack backpack, Mine mine, Pickaxe pickaxe) {
        player.setGoldAmount(100.0);
        player.setHealth(100.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setBackpack(backpack);
        player.setCurrentMine(mine);
        player.setPickaxe(pickaxe);
        return playerDAO.addPlayer(player);
    }

    public Player buyItem(Integer id, Item item) {
        Player player = getPlayerById(id);
        if (item instanceof FoodItem) {
            Backpack backpack = player.getBackpack();
            backpack.getFoodItems().add((FoodItem) item);
        } else {
            player.setPickaxe((Pickaxe) item);
        }
        player.setGoldAmount(player.getGoldAmount() - item.getItemPrice());
        return playerDAO.addPlayer(player);
    }


    public Player dig(Integer id) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        Player player = maybePlayer.get();
        goldDug(player);
        increaseGoldAmount(player);
        decreaseHealth(player);
        decreasePickaxeCondition(player);
        decreaseAmountGoldInMine(player);
        return playerDAO.addPlayer(player);
    }

    public double hitWithPickaxe(Player player) {
        Random random = new Random();
        double randomHit = Math.round(random.nextDouble(0.0, 1.0) * 10.0) / 10.0;
        double totalHit = randomHit * (player.getHealth() / 100)
                * player.getPickaxe().getStrength()
                * (player.getPickaxe().getCondition() / 100);
        return totalHit;
    }

    public double goldDug(Player player) {
        return player.getCurrentMine().getTotalGold() * hitWithPickaxe(player)
                * (1 - player.getCurrentMine().getDifficulty());
    }

    public void increaseGoldAmount(Player player) {
        Double newAmount = player.getGoldAmount() + goldDug(player);
        player.setGoldAmount(newAmount);
    }

    public void decreaseHealth(Player player) {
        Random random = new Random();
        Double newHealth = player.getHealth() - (player.getHealth() * hitWithPickaxe(player)
                * player.getCurrentMine().getDifficulty()
                * random.nextDouble(0, 100) * 10.0 / 10.0);
        if (newHealth <= 0) {
            die(player);
        }
        player.setHealth(newHealth);
    }

    public void die(Player player) {
        player.setHealth(0);
    }

    public void decreasePickaxeCondition(Player player) {
        Random random = new Random();
        Double newCondition = player.getPickaxe().getCondition() - (player.getPickaxe().getCondition()
                * hitWithPickaxe(player) * player.getCurrentMine().getDifficulty()
                * random.nextDouble(0, 100) * 10.0 / 10.0);
        if (newCondition <= 0) {
            wastePickaxe(player);
        }
        player.getPickaxe().setCondition(newCondition);
    }

    public void wastePickaxe(Player player) {
        player.setPickaxe(null);
    }

    public void decreaseAmountGoldInMine(Player player) {
        Double newTotalGoldInMine = player.getCurrentMine().getTotalGold() - goldDug(player);
        if (newTotalGoldInMine <= 0) {
            closeMine(player);
        }
        player.getCurrentMine().setTotalGold(newTotalGoldInMine);
    }

    public void closeMine(Player player) {
        player.setCurrentMine(null);
    }

    public List<FoodItem> showFoodInBackpack(Integer id) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        Player player = maybePlayer.get();
        return player.getBackpack().getFoodItems();
    }

   /* public FoodItem chooseFoodItemInBackpack(Integer id) {
        List<FoodItem> items = showFoodInBackpack()
         Optional<FoodItem> maybeFoodItem = Optional.ofNullable(showFoodInBackpack(player).stream()
                 .filter(foodItem -> id == foodItem.getItemId())
                 .findFirst().orElse(null));
         return maybeFoodItem.get();
    }
    */


    public double foodItemsMaxHealthIncrement(FoodItem foodItem) {
        return foodItem.getHealthEffect() * foodItem.getWeight() / 100;
    }

    public double healthNeededToFull(Player player) {
        return 100 - player.getHealth();
    }

    public Player eat(Integer id, Integer foodItemId) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        Player player = maybePlayer.get();
        FoodItem foodItem = player.getBackpack().getFoodItems().stream()
                .filter(foodItem1 -> foodItem1.getItemId().equals(foodItemId))
                .findFirst().orElse(null);
        double foodWeightNeeded = 100 * healthNeededToFull(player) / foodItem.getHealthEffect();
        if (foodWeightNeeded > foodItem.getWeight()) {
            double newHealth = player.getHealth() + foodItemsMaxHealthIncrement(foodItem);
            player.setHealth(newHealth);
            player.getBackpack().removeFoodItem(foodItem);
        } else if (foodWeightNeeded <= foodItem.getWeight()) {
            double newFoodWeight = foodItem.getWeight()
                    - (100 * (foodItemsMaxHealthIncrement(foodItem) - healthNeededToFull(player))
                    / foodItem.getHealthEffect());
            player.setHealth(100);
            foodItem.setWeight(newFoodWeight);
        }
        return playerDAO.addPlayer(player);
    }

    public Player sleep(Integer id) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        Player player = maybePlayer.get();
        player.setActionsRemaining(player.getMaxActions());
        return playerDAO.addPlayer(player);
    }



    private Mine createMine() {
        Mine mine = mineService.getAllMines()
                .stream()
                .findAny()
                .get();
        Mine newMine = new Mine();
        newMine.setMineName(mine.getMineName());
        newMine.setTotalGold(mine.getTotalGold());
        newMine.setDifficulty(mine.getDifficulty());
        return newMine;
    }

    private Pickaxe createPickaxe() {
        Pickaxe aPickaxe = pickaxeService.getAllPickaxes()
                .stream()
                .filter(pickaxe -> pickaxe.getItemName().equalsIgnoreCase("Wooden pickaxe"))
                .findAny()
                .get();
        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemName(aPickaxe.getItemName());
        newPickaxe.setStrength(aPickaxe.getStrength());
        newPickaxe.setCondition(aPickaxe.getCondition());
        return newPickaxe;
    }
}
