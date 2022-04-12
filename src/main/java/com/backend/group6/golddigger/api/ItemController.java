package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Item;
import com.backend.group6.golddigger.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") Integer id) {
        return itemService.getItemById(id);
    }

    @PostMapping("/create")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }
}
