package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickaxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PickaxeServiceTest {
    static PickaxeService unitUnderTest;
    @Mock
    static PickaxeDAO pickaxeDAO;

    @BeforeAll
    public static void init() {
        pickaxeDAO = Mockito.mock(PickaxeDAO.class);
        unitUnderTest = new PickaxeService(pickaxeDAO);
    }

    @Test
    void verifyThatGetAllPickaxesReturnsAllPickaxesRegisteredInDatabase() {
        //Setup
        Pickaxe pickaxe1 = new Pickaxe();
        pickaxe1.setItemId(1);
        pickaxe1.setItemName("Wooden pickaxe");
        pickaxe1.setItemPrice(100.0);
        pickaxe1.setStrength(1.0);
        pickaxe1.setCondition(100.0);

        Pickaxe pickaxe2 = new Pickaxe();
        pickaxe2.setItemId(2);
        pickaxe2.setItemName("Gold pickaxe");
        pickaxe2.setItemPrice(500.0);
        pickaxe2.setStrength(1.5);
        pickaxe2.setCondition(100.0);

        List<Pickaxe> pickaxesFromDatabase = List.of(pickaxe1, pickaxe2);
        Mockito.when(pickaxeDAO.getAllPickaxes()).thenReturn(pickaxesFromDatabase);

        //Test
        List<Pickaxe> actualListOfPickaxes = unitUnderTest.getAllPickaxes();

        //Verify
        assertEquals(2,actualListOfPickaxes.size());
    }

    @Test
    void verifyThatGetPickaxeByIdReturnsCorrectPickaxe_WhenCorrectIdIsGiven() {
        // Setup
        Pickaxe pickaxeFromDatabase = new Pickaxe();
        pickaxeFromDatabase.setItemId(1);
        pickaxeFromDatabase.setItemName("Wooden pickaxe");
        pickaxeFromDatabase.setItemPrice(100.0);
        pickaxeFromDatabase.setStrength(1.0);
        pickaxeFromDatabase.setCondition(100.0);
        pickaxeFromDatabase.setPlayer(new Player());

        Mockito.when(pickaxeDAO.getPickaxeById(1)).thenReturn(Optional.of(pickaxeFromDatabase));

        //Test
        Pickaxe actualPickaxe = unitUnderTest.getPickaxeById(1);

        //Verify
        assertNotNull(actualPickaxe);
        assertEquals(1, actualPickaxe.getItemId());
        assertEquals("Wooden pickaxe", actualPickaxe.getItemName());
    }

    @Test
    void verifyThatAddPickaxeReturnsAddedPickaxe() {
        // Setup
        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemId(null);
        newPickaxe.setItemName("Wooden pickaxe");
        newPickaxe.setItemPrice(100.0);
        newPickaxe.setStrength(1.0);
        newPickaxe.setCondition(100.0);
        newPickaxe.setPlayer(new Player());

        Pickaxe pickaxeFromDatabase = new Pickaxe();
        pickaxeFromDatabase.setItemId(1);
        pickaxeFromDatabase.setItemName("Wooden pickaxe");
        pickaxeFromDatabase.setItemPrice(100.0);
        pickaxeFromDatabase.setStrength(1.0);
        pickaxeFromDatabase.setCondition(100.0);
        pickaxeFromDatabase.setPlayer(new Player());

        Mockito.when(pickaxeDAO.addPickaxe(any())).thenReturn(pickaxeFromDatabase);

        // Test
        Pickaxe actualPickaxe = unitUnderTest.addPickaxe(newPickaxe);

        //Verify
        assertEquals(1, actualPickaxe.getItemId());
        assertEquals("Wooden pickaxe", actualPickaxe.getItemName());
    }
}