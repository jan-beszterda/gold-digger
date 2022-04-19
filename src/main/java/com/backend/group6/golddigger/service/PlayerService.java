package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import com.backend.group6.golddigger.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private PlayerDAO playerDAO;
    private MineDAO mineDAO;
    private PickaxeDAO pickaxeDAO;
    private ItemDAO itemDAO;
    private FoodDAO foodDAO;

    public PlayerService(PlayerDAO playerDAO, MineDAO mineDAO, PickaxeDAO pickaxeDAO,
                         ItemDAO itemDAO, FoodDAO foodDAO) {
        this.playerDAO = playerDAO;
        this.mineDAO = mineDAO;
        this.pickaxeDAO = pickaxeDAO;
        this.itemDAO = itemDAO;
        this.foodDAO = foodDAO;
    }

    public List<Player> getAllPlayers() {
        return (List<Player>) playerDAO.findAllPlayers();
    }

    public List<Player> getAllAvailablePlayers() {
        List<Player> availablePlayers = (List<Player>) playerDAO.findAllPlayers();
        return availablePlayers.stream()
                .filter(player -> player.getMaxActions() != 0 && player.getHealth() != 0)
                .collect(Collectors.toList());
    }

    public Player getPlayerById(Integer id) {
        return playerDAO.findPlayerById(id).orElse(null);
    }

    public Player createNewPlayer(Player player) {
        player.setGoldAmount(100.0);
        player.setHealth(100.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setBackpack(createPlayersBackpack(player));
        player.setCurrentMine(createPlayersMine(player));
        player.setPickaxe(createPlayersPickaxe(player));
        return playerDAO.savePlayer(player);
    }

    public Player dig(Integer id) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        if (maybePlayer.isEmpty()) {
            return null;
        }
        Player player = maybePlayer.get();
        double hit = calculatePickaxeHit(player);
        double goldDug = calculateGoldDug(player, hit);
        increasePlayerGoldAmount(player, goldDug);
        decreasePlayersHealth(player);
        decreasePickaxeCondition(player, hit);
        decreaseAmountGoldInMine(player, goldDug);
        int actions = player.getActionsRemaining();
        player.setActionsRemaining(actions-1);
        return playerDAO.savePlayer(player);
    }

    public Player eat(Integer id, Integer foodItemId) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        if (maybePlayer.isEmpty()) {
            return null;
        }
        Player player = maybePlayer.get();
        List<FoodItem> itemsInBackpack = player.getBackpack().getFoodItems();
        Optional<FoodItem> maybeFoodItem = itemsInBackpack
                .stream()
                .filter(foodItem -> foodItem.getItemId().equals(foodItemId))
                .findFirst();
        if (maybeFoodItem.isEmpty()) {
            return null;
        }
        FoodItem foodItem = maybeFoodItem.get();
        double newHealth = player.getHealth() + foodItem.getHealthEffect();
        player.setHealth(newHealth > 100 ? 100 : newHealth);
        itemsInBackpack.remove(foodItem);
        player.getBackpack().setFoodItems(itemsInBackpack);
        int actions = player.getActionsRemaining();
        player.setActionsRemaining(actions-1);
        return playerDAO.savePlayer(player);
    }

    public Player buyItem(Integer id, Integer itemId) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        if (maybePlayer.isEmpty()) {
            return null;
        }
        Player player = maybePlayer.get();
        Optional<Item> maybeItem = itemDAO.findItemById(itemId);
        if (maybeItem.isEmpty()) {
            return null;
        }
        Item item = maybeItem.get();
        if (player.getGoldAmount() < item.getItemPrice()) {
            return null;
        }

        if (item instanceof FoodItem) {
            if ((getCurrentBackpackWeight(player) + ((FoodItem) item).getWeight()) > player.getBackpack().getMaxWeight()) {
                return null;
            }
            Backpack backpack = player.getBackpack();
            List<FoodItem> foodItems = backpack.getFoodItems();
            FoodItem foodItem = new FoodItem();
            foodItem.setItemName(item.getItemName());
            foodItem.setHealthEffect(((FoodItem) item).getHealthEffect());
            foodItem.setWeight(((FoodItem) item).getWeight());
            foodItems.add(foodItem);
            backpack.setFoodItems(foodItems);
            player.setBackpack(backpack);
        } else {
            Pickaxe pickaxe = new Pickaxe();
            pickaxe.setItemName(item.getItemName());
            pickaxe.setStrength(((Pickaxe) item).getStrength());
            pickaxe.setCondition(((Pickaxe) item).getCondition());
            pickaxe.setPlayer(player);
            player.setPickaxe(pickaxe);
        }
        player.setGoldAmount(player.getGoldAmount() - item.getItemPrice());
        int actions = player.getActionsRemaining();
        player.setActionsRemaining(actions-1);
        return playerDAO.savePlayer(player);
    }

    public Player move(Integer playerId) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(playerId);
        if (maybePlayer.isEmpty()) {
            return null;
        }
        Player player = maybePlayer.get();
        player.setCurrentMine(createPlayersMine(player));
        int actions = player.getActionsRemaining();
        player.setActionsRemaining(actions-1);
        return playerDAO.savePlayer(player);
    }

    public Player sleep(Integer id) {
        Optional<Player> maybePlayer = playerDAO.findPlayerById(id);
        if (maybePlayer.isEmpty()) {
            return null;
        }
        Player player = maybePlayer.get();
        decreasePlayersHealth(player);
        player.setActionsRemaining(player.getMaxActions());
        return playerDAO.savePlayer(player);
    }

    private double calculatePickaxeHit(Player player) {
        Random random = new Random();
        double randomHit = Math.round(random.nextDouble(0.0, 1.0) * 10.0) / 10.0;
        return randomHit * (player.getHealth() / 100)
                * player.getPickaxe().getStrength()
                * (player.getPickaxe().getCondition() / 100);
    }

    private double calculateGoldDug(Player player, double hit) {
        double estimatedGold = player.getCurrentMine().getTotalGold() * hit
                * (1 - player.getCurrentMine().getDifficulty());
        return Math.min(estimatedGold, player.getCurrentMine().getTotalGold());
    }

    private void increasePlayerGoldAmount(Player player, double goldDug) {
        double newAmount = player.getGoldAmount() + goldDug;
        player.setGoldAmount(newAmount);
    }

    private void decreasePickaxeCondition(Player player, double hit) {
        Random random = new Random();
        double damage = hit * player.getCurrentMine().getDifficulty() * random.nextInt(10, 21);
        double newCondition = player.getPickaxe().getCondition() - damage;
        Pickaxe pickaxe = player.getPickaxe();
        pickaxe.setCondition(newCondition);
    }

    private void decreaseAmountGoldInMine(Player player, double goldDug) {
        double newTotalGold = player.getCurrentMine().getTotalGold() - goldDug;
        Mine mine = player.getCurrentMine();
        mine.setTotalGold(newTotalGold);
    }

    private void decreasePlayersHealth(Player player) {
        Random random = new Random();
        double healthDecrease = player.getCurrentMine().getDifficulty() * random.nextInt(10, 21);
        double newHealth = player.getHealth() - healthDecrease;
        if (newHealth <= 0) {
            invalidatePlayer(player);
        } else {
            player.setHealth(newHealth);
        }
    }

    private void invalidatePlayer(Player player) {
        player.setHealth(0);
        player.setMaxActions(0);
    }

    private Mine createPlayersMine(Player player) {
        List<Mine> mines = (List<Mine>) mineDAO.findAllMines();
        mines = mines.stream()
                .filter(mine -> mine.getPlayer() == null)
                .toList();
        Random r = new Random();
        Mine mine = mines.get(r.nextInt(0, mines.size()));
        Mine newMine = new Mine();
        newMine.setMineName(mine.getMineName());
        newMine.setTotalGold(mine.getTotalGold());
        newMine.setDifficulty(mine.getDifficulty());
        newMine.setPlayer(player);
        return newMine;
    }


    private Backpack createPlayersBackpack(Player player) {
        Backpack backpack = new Backpack();
        backpack.setMaxWeight(15.0);
        backpack.setFoodItems(getStartingItems());
        backpack.setPlayer(player);
        return backpack;
    }

    private List<FoodItem> getStartingItems() {
        List<FoodItem> startingItems = new ArrayList<>();
        List<FoodItem> foodItems = (List<FoodItem>) foodDAO.findAllFoodItems();
        foodItems.stream()
                .filter(foodItem -> foodItem.getHealthEffect() <= 5)
                .limit(2)
                .toList()
                .stream()
                .forEach(foodItem -> {
                    FoodItem item = new FoodItem();
                    item.setItemName(foodItem.getItemName());
                    item.setWeight(foodItem.getWeight());
                    item.setHealthEffect(foodItem.getHealthEffect());
                    startingItems.add(item);
                });
        return startingItems;
    }

    private Pickaxe createPlayersPickaxe(Player player) {
        List<Pickaxe> pickaxes = (List<Pickaxe>) pickaxeDAO.findAllPickaxes();
        Optional<Pickaxe> maybePickaxe = pickaxes.stream()
                .filter(pickaxe -> pickaxe.getItemName().equalsIgnoreCase("Wooden pickaxe"))
                .findAny();
        if (maybePickaxe.isEmpty()) {
            return null;
        }
        Pickaxe aPickaxe = maybePickaxe.get();
        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemName(aPickaxe.getItemName());
        newPickaxe.setStrength(aPickaxe.getStrength());
        newPickaxe.setCondition(aPickaxe.getCondition());
        newPickaxe.setPlayer(player);
        return newPickaxe;
    }

    private double getCurrentBackpackWeight(Player player) {
        Backpack backpack = player.getBackpack();
        return backpack.getFoodItems()
                .stream()
                .reduce(0.0, ((aDouble, foodItem) -> aDouble + foodItem.getWeight()), Double::sum);
    }
}
