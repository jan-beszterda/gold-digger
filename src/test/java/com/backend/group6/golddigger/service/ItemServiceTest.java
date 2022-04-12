package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ItemDAO;
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

class ItemServiceTest extends MockitoExtension {
    static ItemService unitUnderTest;
    @Mock
    static ItemDAO itemDAO;

    @BeforeAll
    static void init() {
        itemDAO = Mockito.mock(ItemDAO.class);
        unitUnderTest = new ItemService(itemDAO);
    }

    @Test
    void verifyThatGetAllItemsReturnsAllItemsRegisteredInDatabase() {
        // Setup
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("Wooden pickaxe");
        item1.setItemPrice(100.0);

        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("Apple");
        item2.setItemPrice(15.0);

        List<Item> itemsFromDatabase = List.of(item1, item2);
        Mockito.when(itemDAO.getAllItems()).thenReturn(itemsFromDatabase);

        // Test
        List<Item> actualItems = unitUnderTest.getAllItems();

        // Verify
        assertEquals(2, actualItems.size());
    }

    @Test
    void verifyThatGetItemByIdReturnsCorrectItem_whenCorrectItemIdIsGiven() {
        // Setup
        Item itemFromDatabase = new Item();
        itemFromDatabase.setItemId(1);
        itemFromDatabase.setItemName("Wooden pickaxe");
        itemFromDatabase.setItemPrice(100.0);

        Mockito.when(itemDAO.getItemById(1)).thenReturn(Optional.of(itemFromDatabase));

        //Test
        Item actualItem = unitUnderTest.getItemById(1);

        //Verify
        assertNotNull(actualItem);
        assertEquals(1, actualItem.getItemId());
    }

    @Test
    void verifyThatAddItemAddsItemToDatabaseAndReturnsAddedItem() {
        // Setup
        Item newItem = new Item();
        newItem.setItemId(null);
        newItem.setItemName("Wooden pickaxe");
        newItem.setItemPrice(100.0);

        Item itemFromDatabase = new Item();
        itemFromDatabase.setItemId(1);
        itemFromDatabase.setItemName("Wooden pickaxe");
        itemFromDatabase.setItemPrice(100.0);

        Mockito.when(itemDAO.addItem(any())).thenReturn(itemFromDatabase);

        // Test
        Item actualItem = unitUnderTest.addItem(newItem);

        //Verify
        assertEquals(1, actualItem.getItemId());
        assertEquals("Wooden pickaxe", actualItem.getItemName());
    }
}