package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import com.backend.group6.golddigger.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PlayerServiceTest extends MockitoExtension {
    static PlayerService unitUnderTest;
    @Mock
    static PlayerDAO playerDAO;
    @Mock
    static BackpackDAO backpackDAO;
    @Mock
    static MineDAO mineDAO;
    @Mock
    static PickaxeDAO pickaxeDAO;
    @Mock
    static ItemDAO itemDAO;
    @Mock
    static FoodDAO foodDAO;

    @BeforeAll
    static void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        backpackDAO = Mockito.mock(BackpackDAO.class);
        mineDAO = Mockito.mock(MineDAO.class);
        pickaxeDAO = Mockito.mock(PickaxeDAO.class);
        itemDAO = Mockito.mock(ItemDAO.class);
        foodDAO = Mockito.mock(FoodDAO.class);
        unitUnderTest = new PlayerService(playerDAO, backpackDAO, mineDAO, pickaxeDAO, itemDAO, foodDAO);
    }

    @Test
    void getAllPlayers() {
        // Setup
        List<Player> playersFromDB = new ArrayList<>();

        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Robert");

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setPlayerName("Peter");

        playersFromDB.add(player1);
        playersFromDB.add(player2);

        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        // Test
        List<Player> actualPlayers = unitUnderTest.getAllPlayers();

        // Verify
        assertEquals(2, actualPlayers.size());
    }

    @Test
    void getAllAvailablePlayers() {
        // Setup
        List<Player> playersFromDB = new ArrayList<>();

        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Robert");
        player1.setHealth(50);
        player1.setMaxActions(3);

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setPlayerName("Peter");
        player2.setHealth(0);
        player2.setMaxActions(0);

        playersFromDB.add(player1);
        playersFromDB.add(player2);

        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        // Test
        List<Player> actualPlayers = unitUnderTest.getAllAvailablePlayers();

        // Verify
        assertEquals(1, actualPlayers.size());
    }

    @Test
    void getPlayerById() {
        // Setup
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Robert");

        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player1));

        // Test
        Player actualPlayer = unitUnderTest.getPlayerById(1);

        // Verify
        assertEquals(1, actualPlayer.getPlayerId());
    }

    @Test
    void createNewPlayer() {
        // Setup
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Robert");

        Player playerFromDB = new Player();
        playerFromDB.setPlayerId(1);
        playerFromDB.setPlayerName("Robert");

        Mockito.when(playerDAO.savePlayer(any())).thenReturn(playerFromDB);

        // Test
        Player createdPlayer = unitUnderTest.createNewPlayer(player1);

        // Verify
        assertEquals(1, createdPlayer.getPlayerId());
        assertEquals("Robert", createdPlayer.getPlayerName());


    }

    @Test
    void dig() {
        // Setup
        Player player = new Player();
        Mine mine = new Mine();
        Pickaxe pickaxe = new Pickaxe();

        mine.setMineId(1);
        mine.setMineName("Jan's mine");
        mine.setDifficulty(0.5);
        mine.setTotalGold(1000);
        mine.setPlayer(player);

        pickaxe.setItemId(1);
        pickaxe.setItemName("Jan's pickaxe");
        pickaxe.setStrength(2.5);
        pickaxe.setCondition(100);
        pickaxe.setItemPrice(500);
        pickaxe.setPlayer(player);

        player.setPlayerId(1);
        player.setPlayerName("Jan");
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setHealth(100);
        player.setCurrentMine(mine);
        player.setPickaxe(pickaxe);


        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(player);

        // Test
        Player updatedPlayer = unitUnderTest.dig(1);

        // Verify
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(any());
        assertEquals(2, updatedPlayer.getActionsRemaining());
    }

    @Test
    void verifyThatEatChangesPlayersHealthAndRemovesItemFromBackpackAndLowersPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player playerToDine = new Player();
        playerToDine.setPlayerId(40);
        playerToDine.setPlayerName("Jan");
        playerToDine.setHealth(56.0);
        playerToDine.setGoldAmount(5000.0);
        playerToDine.setMaxActions(3);
        playerToDine.setActionsRemaining(3);
        playerToDine.setCurrentMine(new Mine());
        playerToDine.setPickaxe(new Pickaxe());

        Backpack backpack = new Backpack();
        backpack.setBackpackId(1);
        backpack.setMaxWeight(15.0);

        FoodItem foodItem1 = new FoodItem();
        foodItem1.setItemId(1);
        foodItem1.setItemName("Apple");
        foodItem1.setItemPrice(15.0);
        foodItem1.setHealthEffect(3.0);
        foodItem1.setWeight(0.3);

        FoodItem foodItem2 = new FoodItem();
        foodItem2.setItemId(2);
        foodItem2.setItemName("Water");
        foodItem2.setItemPrice(10.0);
        foodItem2.setHealthEffect(2.0);
        foodItem2.setWeight(0.5);

        List<FoodItem> items = new ArrayList<>();
        items.add(foodItem1);
        items.add(foodItem2);
        backpack.setFoodItems(items);
        playerToDine.setBackpack(backpack);

        Mockito.when(playerDAO.findPlayerById(40)).thenReturn(Optional.of(playerToDine));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(playerToDine);

        // Test
        Player updatedPlayer = unitUnderTest.eat(40, 2);

        // Verify
        assertEquals(40, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals(58.0, updatedPlayer.getHealth());
        assertEquals(1, updatedPlayer.getBackpack().getFoodItems().size());

        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(40);
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(playerToDine);
    }

    @Test
    void verifyThatBuyItemChangesPlayersPickaxeAndLowersPlayersGoldAndLowersPlayersActionsAndReturnsUpdatedPlayer_WhenAPickaxeIsBought() {
        // Setup
        Player playerToMakePurchase = new Player();
        playerToMakePurchase.setPlayerId(30);
        playerToMakePurchase.setPlayerName("Jan");
        playerToMakePurchase.setHealth(100.0);
        playerToMakePurchase.setGoldAmount(5000.0);
        playerToMakePurchase.setMaxActions(3);
        playerToMakePurchase.setActionsRemaining(3);
        playerToMakePurchase.setBackpack(new Backpack());
        playerToMakePurchase.setCurrentMine(new Mine());

        Pickaxe pickaxe = new Pickaxe();
        pickaxe.setItemId(1);
        pickaxe.setItemName("Wooden pickaxe");
        pickaxe.setItemPrice(100.0);
        pickaxe.setStrength(1.0);
        pickaxe.setCondition(100.0);
        pickaxe.setPlayer(playerToMakePurchase);

        playerToMakePurchase.setPickaxe(pickaxe);

        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemId(2);
        newPickaxe.setItemName("Diamond pickaxe");
        newPickaxe.setItemPrice(2000.0);
        newPickaxe.setStrength(2.3);
        newPickaxe.setCondition(100.0);

        Mockito.when(playerDAO.findPlayerById(30)).thenReturn(Optional.of(playerToMakePurchase));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(playerToMakePurchase);
        Mockito.when(itemDAO.getItemById(2)).thenReturn(Optional.of(newPickaxe));

        // Test
        Player updatedPlayer = unitUnderTest.buyItem(30, 2);

        // Verify
        assertEquals(30, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals(3000.0, updatedPlayer.getGoldAmount());
        assertEquals("Diamond pickaxe", updatedPlayer.getPickaxe().getItemName());

        Mockito.verify(itemDAO, Mockito.times(1)).getItemById(2);
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(30);
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(playerToMakePurchase);
    }

    @Test
    void verifyThatMoveChangesPlayersMineAndLowersPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player playerToMove = new Player();
        playerToMove.setPlayerId(20);
        playerToMove.setPlayerName("Jan");
        playerToMove.setHealth(100.0);
        playerToMove.setGoldAmount(100.0);
        playerToMove.setMaxActions(3);
        playerToMove.setActionsRemaining(3);
        playerToMove.setBackpack(new Backpack());
        playerToMove.setPickaxe(new Pickaxe());

        Mine mine = new Mine();
        mine.setMineId(1);
        mine.setMineName("Mine in the woods");
        mine.setTotalGold(1000.0);
        mine.setDifficulty(0.3);
        mine.setPlayer(playerToMove);

        playerToMove.setCurrentMine(mine);

        Mine otherMine = new Mine();
        otherMine.setMineId(2);
        otherMine.setMineName("Deserted mine");
        otherMine.setTotalGold(3000.0);
        otherMine.setDifficulty(0.6);

        Mockito.when(playerDAO.findPlayerById(20)).thenReturn(Optional.of(playerToMove));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(playerToMove);
        Mockito.when(mineDAO.getAllMines()).thenReturn(List.of(otherMine));

        // Test
        Player updatedPlayer = unitUnderTest.move(20);

        // Verify
        assertEquals(20, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals("Deserted mine", updatedPlayer.getCurrentMine().getMineName());

        Mockito.verify(mineDAO, Mockito.times(1)).getAllMines();
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(20);
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(playerToMove);
    }

    @Test
    void verifyThatSleepRestoresPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player playerToSleep = new Player();
        playerToSleep.setPlayerId(10);
        playerToSleep.setPlayerName("Jan");
        playerToSleep.setHealth(100.0);
        playerToSleep.setGoldAmount(100.0);
        playerToSleep.setMaxActions(3);
        playerToSleep.setActionsRemaining(2);
        playerToSleep.setBackpack(new Backpack());
        playerToSleep.setPickaxe(new Pickaxe());
        playerToSleep.setCurrentMine(new Mine());

        Mockito.when(playerDAO.findPlayerById(10)).thenReturn(Optional.of(playerToSleep));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(playerToSleep);

        // Test
        Player updatedPlayer = unitUnderTest.sleep(10);

        // Verify
        assertEquals(3, updatedPlayer.getActionsRemaining());
        assertEquals(10, updatedPlayer.getPlayerId());
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(10);
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(playerToSleep);
    }
}