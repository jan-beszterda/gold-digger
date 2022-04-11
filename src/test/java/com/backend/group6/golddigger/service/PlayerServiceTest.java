package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import com.backend.group6.golddigger.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceTest extends MockitoExtension {

    static PlayerService unitUnderTest;
    static PlayerDAO playerDAO;
    static BackpackDAO backpackDAO;
    static MineDAO mineDAO;
    static PickaxeDAO pickaxeDAO;
    static ItemDAO itemDAO;
    static FoodDAO foodDAO;

    @BeforeAll
    public static void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        backpackDAO = Mockito.mock(BackpackDAO.class);
        mineDAO = Mockito.mock(MineDAO.class);
        pickaxeDAO = Mockito.mock(PickaxeDAO.class);
        itemDAO = Mockito.mock(ItemDAO.class);
        foodDAO = Mockito.mock(FoodDAO.class);

        unitUnderTest = new PlayerService(playerDAO, backpackDAO, mineDAO, pickaxeDAO,
                itemDAO, foodDAO);
    }

    @Test
    @DisplayName("Verify that getPlayers() returns all players from DB")
    void getAllPlayersShouldReturnTrueIfNOTEmpty() {
        //Setup
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Peter");

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setPlayerName("David");

        List<Player> playersFromDB = List.of(player1, player2);
        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        //Test
        List<Player> actualListOfPlayers = unitUnderTest.getAllPlayers();
        boolean containingPlayers = (actualListOfPlayers.isEmpty()) ? false : true;

        //Verify
        assertEquals(true, containingPlayers);
    }

    @Test
    @DisplayName("Verify that getPlayers() returns all players from DB")
    void getAllPlayersShouldReturnFalseIfEmpty() {

        List<Player> playersFromDB = List.of();
        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        //Test
        List<Player> actualListOfPlayers = unitUnderTest.getAllPlayers();
        boolean containingPlayers = (actualListOfPlayers.isEmpty()) ? false : true;

        //Verify
        assertEquals(false, containingPlayers);
    }

    @Test
    @DisplayName("Verify that getPlayerById() returns specific player from DB")
    void getPlayerById() {
        //Setup
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Peter");

        Player player2 = new Player();
        player2.setPlayerId(2);
        player2.setPlayerName("David");

        List<Player> playersFromDB = List.of(player1, player2);
        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        //Test
        List<Player> actualListOfPlayers = unitUnderTest.getAllPlayers();

        //Verify
        assertEquals("Peter", actualListOfPlayers.get(0).getPlayerName());
        assertEquals("David", actualListOfPlayers.get(1).getPlayerName());
    }
}