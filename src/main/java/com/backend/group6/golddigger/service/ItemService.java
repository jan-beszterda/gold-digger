package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ItemDAO;
import com.backend.group6.golddigger.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public List<Item> getAllItems() {
        return (List<Item>) itemDAO.getAllItems();
    }

    public Item getItemById(Integer id) {
        return itemDAO.getItemById(id).orElse(null);
    }

    public void addItem(Item item) {
        itemDAO.addItem(item);
    }
}
