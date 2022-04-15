package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.BackpackDAO;
import com.backend.group6.golddigger.model.Backpack;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackpackService {
    private BackpackDAO backpackDAO;

    public BackpackService(BackpackDAO backpackDAO) {
        this.backpackDAO = backpackDAO;
    }

    public List<Backpack> getAllBackpacks() {
        return (List<Backpack>) backpackDAO.findAllBackpacks();
    }

    public Backpack getBackpackById(Integer id) {
        return backpackDAO.findBackpackById(id).orElse(null);
    }

    public Backpack addBackpack(Backpack backpack) {
        return backpackDAO.saveBackpack(backpack);
    }
}
