package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.Backpack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class BackpackServiceTest extends MockitoExtension {
    static BackpackService unitUnderTest;
    static BackpackDAO backpackDAO;

    @BeforeAll
    public static void init() {
        backpackDAO = Mockito.mock(BackpackDAO.class);
        unitUnderTest = new BackpackService(backpackDAO);
    }

    @Test
    @DisplayName("Verify that getAllBackpacks() returns all backpacks from DB")
    void getAllBackpacksShouldReturnTrueIfNOTEmpty() {
        //Setup
        Backpack backpack1 = new Backpack();
        backpack1.setBackpackId(1);
        backpack1.setMaxWeight(8);

        Backpack backpack2 = new Backpack();
        backpack2.setBackpackId(2);
        backpack2.setMaxWeight(9);

        List<Backpack> backpacksFromDB = List.of(backpack1, backpack2);
        Mockito.when(backpackDAO.getAllBackpacks()).thenReturn(backpacksFromDB);

        //Test
        List<Backpack> actualListOfBackpacks = unitUnderTest.getAllBackpacks();

        //Verify
        assertEquals(2, actualListOfBackpacks.size());
    }

    @Test
    @DisplayName("Verify that getAllBackpacks() returns all backpacks from DB")
    void getAllBackpacksShouldReturnFalseIfEmpty() {
        //Setup
        List<Backpack> backpacksFromDB = List.of();
        Mockito.when(backpackDAO.getAllBackpacks()).thenReturn(backpacksFromDB);

        //Test
        List<Backpack> actualListOfBackpacks = unitUnderTest.getAllBackpacks();

        //Verify
        assertEquals(0, actualListOfBackpacks.size());
    }

    @Test
    @DisplayName("Verify that getBackpacksById() returns specific backpack from DB")
    void getBackpackById() {
        //Setup
        Backpack backpack1 = new Backpack();
        backpack1.setBackpackId(1);
        backpack1.setMaxWeight(8);

        Backpack backpack2 = new Backpack();
        backpack2.setBackpackId(2);
        backpack2.setMaxWeight(9);

        List<Backpack> backpacksFromDB = new ArrayList<>();
        Mockito.when(backpackDAO.getAllBackpacks()).thenReturn(backpacksFromDB);

        //Test
        List<Backpack> actualListOfBackpacks = unitUnderTest.getAllBackpacks();

        //Verify
        Assertions.assertAll(() -> assertEquals(8, actualListOfBackpacks.get(0).getMaxWeight()),
                () -> assertEquals(9, actualListOfBackpacks.get(1).getMaxWeight()));
    }

    @Test
    @DisplayName("Verify that saveBackpack() saves into DB")
    void saveBackpack() {
        // Setup
        Backpack backpack1 = new Backpack();
        backpack1.setBackpackId(1);
        backpack1.setMaxWeight(8);

        Backpack backpackFromDB = new Backpack();
        backpackFromDB.setBackpackId(1);
        backpackFromDB.setMaxWeight(8);

        Mockito.when(backpackDAO.saveBackpack(any())).thenReturn(backpackFromDB);

        // Test
        Backpack actualBackpack = unitUnderTest.saveBackpack(backpack1);

        // Verify
        assertEquals(1, actualBackpack.getBackpackId());
        assertEquals(8, actualBackpack.getMaxWeight());
    }


}