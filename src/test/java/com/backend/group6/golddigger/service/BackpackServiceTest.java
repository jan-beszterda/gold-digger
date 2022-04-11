package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.Backpack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        boolean containingBackpacks = (actualListOfBackpacks.isEmpty()) ? false : true;

        //Verify
        assertEquals(true, containingBackpacks);
    }



    @Test
    void getBackpackById() {
    }

    @Test
    void saveBackpack() {
    }
}