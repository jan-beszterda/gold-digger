package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.service.PickaxeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items/pickaxes")
public class PickaxeController {

    PickaxeService pickaxeService;

    public PickaxeController(PickaxeService pickaxeService) {
        this.pickaxeService = pickaxeService;
    }

    @GetMapping()
    public List<Pickaxe> getAllPickaxes() {
        return pickaxeService.getAllPickaxes();
    }

    @GetMapping("/{id}")
    public Pickaxe getPickaxeById(@PathVariable("id") Integer id) {
        return pickaxeService.getPickaxeById(id);
    }

    @PostMapping()
    public void addPickaxe(@RequestBody Pickaxe pickaxe) {
        pickaxeService.addPickaxe(pickaxe);
    }
}
