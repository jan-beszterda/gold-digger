package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import com.backend.group6.golddigger.model.Player;
import org.junit.jupiter.api.BeforeAll;
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
    static ShopDAO shopDAO;

    @BeforeAll
    public static void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        backpackDAO = Mockito.mock(BackpackDAO.class);
        mineDAO = Mockito.mock(MineDAO.class);
        pickaxeDAO = Mockito.mock(PickaxeDAO.class);
        itemDAO = Mockito.mock(ItemDAO.class);
        foodDAO = Mockito.mock(FoodDAO.class);
        shopDAO = Mockito.mock(ShopDAO.class);

        unitUnderTest = new PlayerService(playerDAO, backpackDAO, mineDAO, pickaxeDAO,
                itemDAO, foodDAO, shopDAO);
    }

    @Test
    void getAllPlayers() {
        //Setup
        Player player1 = new Player();
        player1.setPlayerId(1);
        player1.setPlayerName("Peter");

        List<Player> playersFromDB = List.of(player1);
        Mockito.when(playerDAO.findAllPlayers()).thenReturn(playersFromDB);

        //Test
        List<Player> actualListOfPlayers = unitUnderTest.getAllPlayers();

        //Verify
        assertEquals("Peter", actualListOfPlayers.get(0).getPlayerName());
    }
}