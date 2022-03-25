package com.backend.group6.golddigger.controller;

import com.backend.group6.golddigger.service.BackpackService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backpack")
public class BackpackController {

    BackpackService backpackService;

    public BackpackController(BackpackService backpackService) {
        this.backpackService = backpackService;
    }
}
