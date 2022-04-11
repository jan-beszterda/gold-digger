package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickaxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(pickaxeDAO.getPickaxeById(1)).thenReturn((pickaxesFromDatabase
                .stream()
                .filter(pickaxe -> pickaxe.getItemId() == 1))
                .findFirst());

        //Test
        Pickaxe actualPickaxe = unitUnderTest.getPickaxeById(1);

        //Verify
        assertEquals("Wooden pickaxe",actualPickaxe.getItemName());
    }

    @Test
    void verifyThatGetPickaxeByIdReturnsNull_WhenPickaxeWithGivenIdDoesNotExist() {
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
        Mockito.when(pickaxeDAO.getPickaxeById(3)).thenReturn((pickaxesFromDatabase
                .stream()
                .filter(pickaxe -> pickaxe.getItemId() == 3))
                .findFirst());

        //Test
        Pickaxe actualPickaxe = unitUnderTest.getPickaxeById(3);

        //Verify
        assertNull(actualPickaxe);
    }

    @Test
    void verifyThatAddShopReturnsAddedShop() {
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

        Pickaxe pickaxe3 = new Pickaxe();
        pickaxe2.setItemId(3);
        pickaxe2.setItemName("Diamond pickaxe");
        pickaxe2.setItemPrice(1000.0);
        pickaxe2.setStrength(2.3);
        pickaxe2.setCondition(100.0);
        Mockito.when(pickaxeDAO.addPickaxe(pickaxe3)).thenReturn(pickaxe3);

        //Test
        Pickaxe actualPickaxe = unitUnderTest.addPickaxe(pickaxe3);

        //Verify
        assertEquals(pickaxe3, actualPickaxe);
    }
}