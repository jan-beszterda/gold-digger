package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.service.MineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mines")
public class MineController {
    private MineService mineService;

    public MineController(MineService mineService) {
        this.mineService = mineService;
    }

    @GetMapping()
    public List<Mine> getAllMines() {
        return mineService.getAllMines();
    }

    @GetMapping("/{id}")
    public Mine getMineById(@PathVariable("id") Integer id) {
        return mineService.getMineById(id);
    }

    @DeleteMapping("/{id}")
    public void removeMine(@PathVariable("id") Integer id) {
        mineService.removeMine(id);
    }

    @PostMapping("/create")
    public Mine addMine(@RequestBody Mine mine) {
        return mineService.addMine(mine);
    }
}
