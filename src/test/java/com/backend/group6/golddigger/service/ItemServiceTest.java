package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ItemDAO;
import com.backend.group6.golddigger.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Verify that getAllItems() returns all items from database")
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
        Mockito.when(itemDAO.findAllItems()).thenReturn(itemsFromDatabase);

        // Test
        List<Item> actualItems = unitUnderTest.getAllItems();

        // Verify
        assertEquals(2, actualItems.size());
    }

    @Test
    @DisplayName("Verify that getItemById() returns correct item from database")
    void verifyThatGetItemByIdReturnsCorrectItem_whenCorrectItemIdIsGiven() {
        // Setup
        Item itemFromDatabase = new Item();
        itemFromDatabase.setItemId(1);
        itemFromDatabase.setItemName("Wooden pickaxe");
        itemFromDatabase.setItemPrice(100.0);

        Mockito.when(itemDAO.findItemById(1)).thenReturn(Optional.of(itemFromDatabase));

        //Test
        Item actualItem = unitUnderTest.getItemById(1);

        //Verify
        assertNotNull(actualItem);
        assertEquals(1, actualItem.getItemId());
    }

    @Test
    @DisplayName("Verify that addItem() returns saved item")
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

        Mockito.when(itemDAO.saveItem(newItem)).thenReturn(itemFromDatabase);

        // Test
        Item actualItem = unitUnderTest.addItem(newItem);

        //Verify
        assertEquals(1, actualItem.getItemId());
        assertEquals("Wooden pickaxe", actualItem.getItemName());
    }
}