package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.model.Player;
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
    void eat() {
    }

    @Test
    void buyItem() {
    }

    @Test
    void move() {
    }

    @Test
    void sleep() {
    }
}