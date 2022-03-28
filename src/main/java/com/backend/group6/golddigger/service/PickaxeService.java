package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickAxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PickaxeService {

    PickAxeDAO pickAxeDAO;

    public PickaxeService(PickAxeDAO pickAxeDAO) {
        this.pickAxeDAO = pickAxeDAO;
    }

    public List<Pickaxe> getAllPickaxes() {
        return pickAxeDAO.getAllPickaxes();
    }

    public Pickaxe getPickaxeById(UUID id) {
        return pickAxeDAO.getPickaxeById(id).orElse(null);
    }
}
