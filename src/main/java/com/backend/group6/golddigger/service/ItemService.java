package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.ItemDAO;
import com.backend.group6.golddigger.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public List<Item> getAllItems() {
        return (List<Item>) itemDAO.findAllItems();
    }

    public Item getItemById(Integer id) {
        return itemDAO.findItemById(id).orElse(null);
    }

    public Item addItem(Item item) {
        return itemDAO.saveItem(item);
    }
}
