package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import org.springframework.stereotype.Service;

@Service
public class BackpackService {

    BackpackDAO backpackDAO;

    public BackpackService(BackpackDAO backpackDAO) {
        this.backpackDAO = backpackDAO;
    }
}
