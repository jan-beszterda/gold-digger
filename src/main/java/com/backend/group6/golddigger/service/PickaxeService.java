package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickaxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickaxeService {
    PickaxeDAO pickaxeDAO;
    Pickaxe pickaxe;

    public PickaxeService(PickaxeDAO pickAxeDAO) {
        this.pickaxeDAO = pickAxeDAO;
    }

    public List<Pickaxe> getAllPickaxes() {
        return (List<Pickaxe>) pickaxeDAO.getAllPickaxes();
    }


    public Pickaxe getPickaxeById(Integer id) {
        return pickaxeDAO.getPickaxeById(id).orElse(null);
    }
}
