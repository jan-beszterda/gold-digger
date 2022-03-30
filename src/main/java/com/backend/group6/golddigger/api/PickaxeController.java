package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Pickaxe;
import com.backend.group6.golddigger.service.PickaxeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pickaxes")
public class PickaxeController {

    PickaxeService pickaxeService;

    public PickaxeController(PickaxeService pickaxeService) {
        this.pickaxeService = pickaxeService;
    }

    @GetMapping()
    public List<Pickaxe> getAllPickaxes() {
        return pickaxeService.getAllPickaxes();
    }

    /*@GetMapping("/{id}")
    public Pickaxe getPickaxeById(@PathVariable("id") UUID id) {
        return pickaxeService.getPickaxeById(id);
    }*/

}
