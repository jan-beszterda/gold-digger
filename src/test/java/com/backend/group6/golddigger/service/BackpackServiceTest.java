package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.Backpack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BackpackServiceTest extends MockitoExtension {
    private static BackpackService unitUnderTest;
    @Mock
    private static BackpackDAO backpackDAO;

    @BeforeAll
    public static void init() {
        backpackDAO = Mockito.mock(BackpackDAO.class);
        unitUnderTest = new BackpackService(backpackDAO);
    }

    @Test
    @DisplayName("Verify that getAllBackpacks() returns all backpacks from database")
    void getAllBackpacksShouldReturnTrueIfNOTEmpty() {
        //Setup
        Backpack backpack1 = new Backpack();
        backpack1.setBackpackId(1);
        backpack1.setMaxWeight(8);

        Backpack backpack2 = new Backpack();
        backpack2.setBackpackId(2);
        backpack2.setMaxWeight(9);

        List<Backpack> backpacksFromDatabase = List.of(backpack1, backpack2);
        Mockito.when(backpackDAO.findAllBackpacks()).thenReturn(backpacksFromDatabase);

        //Test
        List<Backpack> actualListOfBackpacks = unitUnderTest.getAllBackpacks();

        //Verify
        assertEquals(2, actualListOfBackpacks.size());
    }

    @Test
    @DisplayName("Verify that getBackpacksById() returns specific backpack from database")
    void verifyThatGetBackpackByIdReturnsCorrectBackpack_whenCorrectIdIsGiven() {
        //Setup
        Backpack backpackFromDatabase = new Backpack();
        backpackFromDatabase.setBackpackId(1);
        backpackFromDatabase.setMaxWeight(8);

        Mockito.when(backpackDAO.findBackpackById(1)).thenReturn(Optional.of(backpackFromDatabase));

        //Test
        Backpack actualBackpack = unitUnderTest.getBackpackById(1);

        //Verify
        assertNotNull(actualBackpack);
        assertEquals(1, actualBackpack.getBackpackId());
    }

    @Test
    @DisplayName("Verify that saveBackpack() saves backpack into database and returns saved item")
    void verifyThatSaveBackpackReturnsSavedBackpack() {
        // Setup
        Backpack newBackpack = new Backpack();
        newBackpack.setBackpackId(null);
        newBackpack.setMaxWeight(8);

        Backpack backpackFromDB = new Backpack();
        backpackFromDB.setBackpackId(1);
        backpackFromDB.setMaxWeight(8);

        Mockito.when(backpackDAO.saveBackpack(newBackpack)).thenReturn(backpackFromDB);

        // Test
        Backpack actualBackpack = unitUnderTest.addBackpack(newBackpack);

        // Verify
        assertEquals(1, actualBackpack.getBackpackId());
        assertEquals(8, actualBackpack.getMaxWeight());
    }
}