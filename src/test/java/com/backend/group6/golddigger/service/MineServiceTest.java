package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    @DisplayName("Verify that getAllMines() returns all mines from DB")
    void getMineByIdShouldReturnFalseIfEmpty() {
        //Setup
        List<Mine> minesFromDB = List.of();
        Mockito.when(mineDAO.getAllMines()).thenReturn(minesFromDB);

        //Test
        List<Mine> actualListOfMines = unitUnderTest.getAllMines();
        boolean containingMines = (actualListOfMines.isEmpty() ? false : true);

        //Verify
        assertEquals(false, containingMines);
    }

    @Test
    @DisplayName("Verify that getMineById() returns specific mine from DB")
    void getMineById() {
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
        Assertions.assertAll(() -> assertEquals("Mine1", actualListOfMines.get(0).getMineName()),
                () -> assertEquals("Mine2", actualListOfMines.get(1).getMineName()));
    }

  /*  @Test
    void removeMine() {
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
        mineDAO.deleteMine(mine1.getMineId());
        List<Mine> actualListOfMines = unitUnderTest.getAllMines();

        boolean containingMine = actualListOfMines.contains(mine1);

        //Verify
        assertEquals(false, containingMine);
    }*/

    @Test
    void addMine() {
    }


}