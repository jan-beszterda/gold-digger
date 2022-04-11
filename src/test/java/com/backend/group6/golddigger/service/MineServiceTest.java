package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MineServiceTest extends MockitoExtension {
    static MineService unitUnderTest;
    static MineDAO mineDAO;

    @BeforeAll
    public static void init() {
        mineDAO = Mockito.mock(MineDAO.class);
        unitUnderTest = new MineService(mineDAO);
    }


    @Test
    @DisplayName("Verify that getAllMines() returns all mines from DB")
    void getAllMinesShouldReturnTrueIfNOTEmpty() {
        //Setup
        Mine mine1 = new Mine();
        mine1.setMineId(1);
        mine1.setMineName("Mine1");

        Mine mine2 = new Mine();
        mine2.setMineId(2);
        mine2.setMineName("Mine2");

        List<Mine> minesFromDB = List.of(mine1, mine2);
        Mockito.when(mineDAO.getAllMines()).thenReturn(minesFromDB);

        //Test
        List<Mine> actualListOfMines = unitUnderTest.getAllMines();
        boolean containingMines = (actualListOfMines.isEmpty() ? false : true);

        //Verify
        assertEquals(true, containingMines);
    }

    @Test
    void getMineById() {
    }

    @Test
    void removeMine() {
    }

    @Test
    void addMine() {
    }
}