package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ShopDAO;
import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.model.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ShopServiceTest extends MockitoExtension {
    static ShopService unitUnderTest;
    @Mock
    static ShopDAO shopDAO;

    @BeforeAll
    public static void init() {
        shopDAO = Mockito.mock(ShopDAO.class);
        unitUnderTest = new ShopService(shopDAO);
    }

    @Test
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
        Mockito.when(shopDAO.getAllShops()).thenReturn(shopsFromDatabase);

        //Test
        List<Shop> actualListOfShops = unitUnderTest.getAllShops();

        //Verify
        assertEquals(2,actualListOfShops.size());
    }

    @Test
    void verifyThatGetShopByIdReturnsCorrectShop_WhenCorrectIdIsGiven() {
        // Setup
        Shop shopFromDatabase = new Shop();
        shopFromDatabase.setShopId(1);
        shopFromDatabase.setShopName("Diggers' den");
        shopFromDatabase.setShopInventory(List.of(new Item()));

        Mockito.when(shopDAO.getShopById(1)).thenReturn(Optional.of(shopFromDatabase));

        //Test
        Shop actualShop = unitUnderTest.getShopById(1);

        //Verify
        assertNotNull(actualShop);
        assertEquals(1, actualShop.getShopId());
        assertEquals("Diggers' den", actualShop.getShopName());
    }

    @Test
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

        Mockito.when(shopDAO.addShop(any())).thenReturn(shopFromDatabase);

        // Test
        Shop actualShop = unitUnderTest.addShop(newShop);

        //Verify
        assertEquals(1, actualShop.getShopId());
        assertEquals("Diggers' den", actualShop.getShopName());
    }
}