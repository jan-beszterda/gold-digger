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

import static org.junit.jupiter.api.Assertions.*;

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
        boolean containingFoodItems = (actualListOfFoodItems.isEmpty()) ? false : true;

        //Verify
        assertEquals(true, containingFoodItems);

    }

    @Test
    @DisplayName("Verify that getAllFoodItems() returns all foodItems from DB")
    void getAllFoodItemsShouldReturnFalseIfEmpty() {
        //Setup
        List<FoodItem> foodFromDB = List.of();
        Mockito.when(foodDAO.getAllFoodItems()).thenReturn(foodFromDB);

        //Test
        List<FoodItem> actualListOfFoodItems = unitUnderTest.getAllFoodItems();
        boolean containingFoodItems = (actualListOfFoodItems.isEmpty()) ? false : true;

        //Verify
        assertEquals(false, containingFoodItems);

    }

    @Test
    @DisplayName("Verify that getFoodItemById() returns specific foodItem from DB")
    void getFoodItemById() {
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
        Assertions.assertAll(() -> assertEquals("Coca Cola", actualListOfFoodItems.get(0).getItemName()),
                () -> assertEquals("Pepsi", actualListOfFoodItems.get(1).getItemName()));

    }

    @Test
    void addFoodItem() {
    }
}