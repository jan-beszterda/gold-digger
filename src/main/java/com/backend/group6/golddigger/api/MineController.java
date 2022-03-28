package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.service.MineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mine")
public class MineController {

    MineService mineService;

    public MineController(MineService mineService) {
        this.mineService = mineService;
    }

    @GetMapping()
    public List<Mine> getMines() {
        return mineService.getAllMines();
    }

    @GetMapping("/{id}")
    public Mine getMineById(@PathVariable("id") UUID id) {
        return mineService.getMineById(id);
    }

    //@DeleteMapping("{id}")
    // public void removeMine(@PathVariable("id") UUID id) {
    //mineService.removeMine(id);}

}
