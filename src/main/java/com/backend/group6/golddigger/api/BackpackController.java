package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.service.BackpackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backpacks")
public class BackpackController {
    BackpackService backpackService;

    public BackpackController(BackpackService backpackService) {
        this.backpackService = backpackService;
    }

    @GetMapping()
    public List<Backpack> getAllBackpacks() {
        return backpackService.getAllBackpacks();
    }

    @GetMapping("/{id}")
    public Backpack getBackpackById(@PathVariable("id") Integer id) {
        return backpackService.getBackpackById(id);
    }

    @PostMapping()
    public Backpack addBackpack(@RequestBody Backpack backpack) {
        return backpackService.saveBackpack(backpack);
    }
}
