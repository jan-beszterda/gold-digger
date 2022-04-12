package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.FoodDAO;
import com.backend.group6.golddigger.model.FoodItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class FoodServiceTest extends MockitoExtension {

    static FoodService unitUnderTest;
    static FoodDAO foodDAO;

    @BeforeAll
    public static void init() {
        foodDAO = Mockito.mock(FoodDAO.class);
        unitUnderTest = new FoodService(foodDAO);
    }

    @Test
    @DisplayName("Verify that getAllFoodItems() returns all foodItems from DB")
    void getAllFoodItemsShouldReturnTrueIfNOTEmpty() {
        //Setup
        FoodItem foodItem1 = new FoodItem();
        foodItem1.setItemId(1);
        foodItem1.setItemName("Coca Cola");

        FoodItem foodItem2 = new FoodItem();
        foodItem2.setItemId(2);
        foodItem2.setItemName("Pepsi");

        List<FoodItem> foodFromDB = List.of(foodItem1, foodItem2);
        Mockito.when(foodDAO.getAllFoodItems()).thenReturn(foodFromDB);

        //Test
        List<FoodItem> actualListOfFoodItems = unitUnderTest.getAllFoodItems();

        //Verify
        assertEquals(2, actualListOfFoodItems.size());
    }

    @Test
    @DisplayName("Verify that getFoodItemById() returns specific foodItem from DB")
    void verifyThatGetFoodItemByIdReturnsCorrectFoodItem_whenCorrectIdIsGiven() {
        //Setup
        FoodItem foodItemFromDatabase = new FoodItem();
        foodItemFromDatabase.setItemId(1);
        foodItemFromDatabase.setItemName("Coca Cola");

        Mockito.when(foodDAO.getFoodItemById(1)).thenReturn(Optional.of(foodItemFromDatabase));

        //Test
        FoodItem actualFoodItem = unitUnderTest.getFoodItemById(1);

        //Verify
        assertNotNull(actualFoodItem);
        assertEquals(1, actualFoodItem.getItemId());
    }

    @Test
    @DisplayName("Verify that addFoodItem() saves into DB")
    void verifyThatAddFoodItemReturnsAddedItem() {
        // Setup
        FoodItem foodItem = new FoodItem();
        foodItem.setItemId(null);
        foodItem.setItemName("Tomato");

        FoodItem foodItemFromDB = new FoodItem();
        foodItemFromDB.setItemId(1);
        foodItemFromDB.setItemName("Tomato");

        Mockito.when(foodDAO.addFoodItem(any())).thenReturn(foodItemFromDB);

        // Test
        FoodItem actualFoodItem = unitUnderTest.addFoodItem(foodItem);

        // Verify
        assertEquals(1, actualFoodItem.getItemId());
        assertEquals("Tomato", actualFoodItem.getItemName());
    }
}