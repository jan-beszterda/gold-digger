package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Backpack;
import com.backend.group6.golddigger.service.BackpackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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
}
