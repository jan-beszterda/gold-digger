package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

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
    void getAllMinesShouldReturnAllMinesInDatabase() {
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

        //Verify
        assertEquals(2, actualListOfMines.size());
    }

    @Test
    @DisplayName("Verify that getMineById() returns specific mine from DB")
    void verifyThatGetMineByIdReturnCorrectMine_whenCorrectIdIsGiven() {
        //Setup
        Mine mineFromDatabase = new Mine();
        mineFromDatabase.setMineId(1);
        mineFromDatabase.setMineName("A mine");

        Mockito.when(mineDAO.getMineByID(1)).thenReturn(Optional.of(mineFromDatabase));

        //Test
        Mine actualMine = unitUnderTest.getMineById(1);

        //Verify
        assertEquals(1, actualMine.getMineId());
        assertEquals("A mine", actualMine.getMineName());
    }

    @Test
    void verifyThatRemoveMineCallsOnlyDeleteMineMethodAndThatDeleteMethodIsCalledOnlyOnce() {
        //Setup

        //Test
        unitUnderTest.removeMine(99);

        //Verify
        Mockito.verify(mineDAO, Mockito.times(1)).deleteMine(99);
    }

    @Test
    void verifyThatAddMineReturnsAddedMine() {
        // Setup
        Mine newMine = new Mine();
        newMine.setMineId(null);
        newMine.setMineName("A mine");

        Mine mineFromDatabase = new Mine();
        mineFromDatabase.setMineId(1);
        mineFromDatabase.setMineName("A mine");

        Mockito.when(mineDAO.saveMine(any())).thenReturn(mineFromDatabase);

        // Test
        Mine actualMine = unitUnderTest.addMine(newMine);

        //Verify
        assertEquals(1, actualMine.getMineId());
        assertEquals("A mine", actualMine.getMineName());
    }
}