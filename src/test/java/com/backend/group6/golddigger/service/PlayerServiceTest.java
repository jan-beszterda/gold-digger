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
    }

    @Test
    void getAllAvailablePlayers() {
    }

    @Test
    void getPlayerById() {
    }

    @Test
    void createNewPlayer() {
    }

    @Test
    void dig() {
    }

    @Test
    void verifyThatEatChangesPlayersHealthAndRemovesItemFromBackpackAndLowersPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Jan");
        player.setHealth(56.0);
        player.setGoldAmount(5000.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setCurrentMine(new Mine());
        player.setPickaxe(new Pickaxe());

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
        player.setBackpack(backpack);

        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(player);

        // Test
        Player updatedPlayer = unitUnderTest.eat(1, 2);

        // Verify
        assertEquals(1, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals(58.0, updatedPlayer.getHealth());
        assertEquals(1, updatedPlayer.getBackpack().getFoodItems().size());

        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(any());
    }

    @Test
    void verifyThatBuyItemChangesPlayersPickaxeAndLowersPlayersGoldAndLowersPlayersActionsAndReturnsUpdatedPlayer_WhenAPickaxeIsBought() {
        // Setup
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Jan");
        player.setHealth(100.0);
        player.setGoldAmount(5000.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setBackpack(new Backpack());
        player.setCurrentMine(new Mine());

        Pickaxe pickaxe = new Pickaxe();
        pickaxe.setItemId(1);
        pickaxe.setItemName("Wooden pickaxe");
        pickaxe.setItemPrice(100.0);
        pickaxe.setStrength(1.0);
        pickaxe.setCondition(100.0);
        pickaxe.setPlayer(player);

        player.setPickaxe(pickaxe);

        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemId(2);
        newPickaxe.setItemName("Diamond pickaxe");
        newPickaxe.setItemPrice(2000.0);
        newPickaxe.setStrength(2.3);
        newPickaxe.setCondition(100.0);

        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(player);
        Mockito.when(itemDAO.getItemById(2)).thenReturn(Optional.of(newPickaxe));

        // Test
        Player updatedPlayer = unitUnderTest.buyItem(1, 2);

        // Verify
        assertEquals(1, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals(3000.0, updatedPlayer.getGoldAmount());
        assertEquals("Diamond pickaxe", updatedPlayer.getPickaxe().getItemName());

        Mockito.verify(itemDAO, Mockito.times(1)).getItemById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(any());
    }

    @Test
    void verifyThatMoveChangesPlayersMineAndLowersPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Jan");
        player.setHealth(100.0);
        player.setGoldAmount(100.0);
        player.setMaxActions(3);
        player.setActionsRemaining(3);
        player.setBackpack(new Backpack());
        player.setPickaxe(new Pickaxe());

        Mine mine = new Mine();
        mine.setMineId(1);
        mine.setMineName("Mine in the woods");
        mine.setTotalGold(1000.0);
        mine.setDifficulty(0.3);
        mine.setPlayer(player);

        player.setCurrentMine(mine);

        Mine otherMine = new Mine();
        otherMine.setMineId(2);
        otherMine.setMineName("Deserted mine");
        otherMine.setTotalGold(3000.0);
        otherMine.setDifficulty(0.6);

        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(player);
        Mockito.when(mineDAO.getAllMines()).thenReturn(List.of(otherMine));

        // Test
        Player updatedPlayer = unitUnderTest.move(1);

        // Verify
        assertEquals(1, updatedPlayer.getPlayerId());
        assertEquals(2, updatedPlayer.getActionsRemaining());
        assertEquals("Deserted mine", updatedPlayer.getCurrentMine().getMineName());

        Mockito.verify(mineDAO, Mockito.times(1)).getAllMines();
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(any());
    }

    @Test
    void verifyThatSleepRestoresPlayersActionsAndReturnsUpdatedPlayer() {
        // Setup
        Player player = new Player();
        player.setPlayerId(1);
        player.setPlayerName("Jan");
        player.setHealth(100.0);
        player.setGoldAmount(100.0);
        player.setMaxActions(3);
        player.setActionsRemaining(2);
        player.setBackpack(new Backpack());
        player.setPickaxe(new Pickaxe());
        player.setCurrentMine(new Mine());

        Mockito.when(playerDAO.findPlayerById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerDAO.savePlayer(any())).thenReturn(player);

        // Test
        Player updatedPlayer = unitUnderTest.sleep(1);

        // Verify
        assertEquals(3, updatedPlayer.getActionsRemaining());
        assertEquals(1, updatedPlayer.getPlayerId());
        Mockito.verify(playerDAO, Mockito.times(1)).findPlayerById(any());
        Mockito.verify(playerDAO, Mockito.times(1)).savePlayer(any());
    }
}