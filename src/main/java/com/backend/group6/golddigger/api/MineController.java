package com.backend.group6.golddigger.api;

import com.backend.group6.golddigger.model.Mine;
import com.backend.group6.golddigger.service.MineService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/mines")
public class MineController {

    MineService mineService;

    public MineController(MineService mineService) {
        this.mineService = mineService;
    }

    @GetMapping()
    public List<Mine> getMines() {
        return mineService.getAllMines();
    }

    /*@GetMapping()
    public Mine getMineById(@PathParam("id") Integer id) {
        return mineService.getMineById(id);
    }*/

    @DeleteMapping()
    public void removeMine(@PathParam("id") Integer id) {
        mineService.removeMine(id);
    }
}
