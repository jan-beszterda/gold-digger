package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickaxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickaxeService {
    private PickaxeDAO pickaxeDAO;

    public PickaxeService(PickaxeDAO pickAxeDAO) {
        this.pickaxeDAO = pickAxeDAO;
    }

    public List<Pickaxe> getAllPickaxes() {
        return (List<Pickaxe>) pickaxeDAO.findAllPickaxes();
    }

    public Pickaxe getPickaxeById(Integer id) {
        return pickaxeDAO.findPickaxeById(id).orElse(null);
    }

    public Pickaxe addPickaxe(Pickaxe pickaxe) {
        return pickaxeDAO.savePickaxe(pickaxe);
    }
}
