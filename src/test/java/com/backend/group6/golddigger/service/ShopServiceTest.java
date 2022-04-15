package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ShopDAO;
import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.model.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest extends MockitoExtension {
    private static ShopService unitUnderTest;
    @Mock
    private static ShopDAO shopDAO;

    @BeforeAll
    public static void init() {
        shopDAO = Mockito.mock(ShopDAO.class);
        unitUnderTest = new ShopService(shopDAO);
    }

    @Test
    @DisplayName("Verify that getAllShops() returns all shops from database")
    void verifyThatGetAllShopsReturnsAllShopsRegisteredInDatabase() {
        //Setup
        Shop shop1 = new Shop();
        shop1.setShopId(1);
        shop1.setShopName("Diggers' den");
        shop1.setShopInventory(List.of(new Item()));

        Shop shop2 = new Shop();
        shop2.setShopId(2);
        shop2.setShopName("Diggers' haven");
        shop2.setShopInventory(List.of(new Item()));

        List<Shop> shopsFromDatabase = List.of(shop1, shop2);
        Mockito.when(shopDAO.findAllShops()).thenReturn(shopsFromDatabase);

        //Test
        List<Shop> actualListOfShops = unitUnderTest.getAllShops();

        //Verify
        assertEquals(2,actualListOfShops.size());
    }

    @Test
    @DisplayName("Verify that getShopById() returns correct shop from database")
    void verifyThatGetShopByIdReturnsCorrectShop_WhenCorrectIdIsGiven() {
        // Setup
        Shop shopFromDatabase = new Shop();
        shopFromDatabase.setShopId(1);
        shopFromDatabase.setShopName("Diggers' den");
        shopFromDatabase.setShopInventory(List.of(new Item()));

        Mockito.when(shopDAO.findShopById(1)).thenReturn(Optional.of(shopFromDatabase));

        //Test
        Shop actualShop = unitUnderTest.getShopById(1);

        //Verify
        assertNotNull(actualShop);
        assertEquals(1, actualShop.getShopId());
        assertEquals("Diggers' den", actualShop.getShopName());
    }

    @Test
    @DisplayName("Verify that addShop() return saved shop")
    void verifyThatAddShopReturnsAddedShop() {
        // Setup
        Shop newShop = new Shop();
        newShop.setShopId(null);
        newShop.setShopName("Diggers' den");
        newShop.setShopInventory(List.of(new Item()));

        Shop shopFromDatabase = new Shop();
        shopFromDatabase.setShopId(1);
        shopFromDatabase.setShopName("Diggers' den");
        shopFromDatabase.setShopInventory(List.of(new Item()));

        Mockito.when(shopDAO.saveShop(newShop)).thenReturn(shopFromDatabase);

        // Test
        Shop actualShop = unitUnderTest.addShop(newShop);

        //Verify
        assertEquals(1, actualShop.getShopId());
        assertEquals("Diggers' den", actualShop.getShopName());
    }
}