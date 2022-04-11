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

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(shopDAO.getShopById(1)).thenReturn((shopsFromDatabase
                .stream()
                .filter(shop -> shop.getShopId() == 1))
                .findFirst());

        //Test
        Shop actualShop = unitUnderTest.getShopById(1);

        //Verify
        assertEquals("Diggers' den",actualShop.getShopName());
    }

    @Test
    void verifyThatGetShopByIdReturnsNull_WhenShopWithGivenIdDoesNotExist() {
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
        Mockito.when(shopDAO.getShopById(3)).thenReturn((shopsFromDatabase
                .stream()
                .filter(shop -> shop.getShopId() == 3))
                .findFirst());

        //Test
        Shop actualShop = unitUnderTest.getShopById(3);

        //Verify
        assertNull(actualShop);
    }

    @Test
    void verifyThatAddShopReturnsAddedShop() {
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

        Shop shop3 = new Shop();
        shop3.setShopId(3);
        shop3.setShopName("New shop");
        shop3.setShopInventory(List.of(new Item()));
        Mockito.when(shopDAO.addShop(shop3)).thenReturn(shop3);

        //Test
        Shop actualShop = unitUnderTest.addShop(shop3);

        //Verify
        assertEquals(shop3, actualShop);
    }
}