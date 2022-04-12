package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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