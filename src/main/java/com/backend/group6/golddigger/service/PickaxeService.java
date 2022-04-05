package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.PickaxeDAO;
import com.backend.group6.golddigger.model.Pickaxe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickaxeService {
    PickaxeDAO pickaxeDAO;

    public PickaxeService(PickaxeDAO pickAxeDAO) {
        this.pickaxeDAO = pickAxeDAO;
    }

    public List<Pickaxe> getAllPickaxes() {
        return (List<Pickaxe>) pickaxeDAO.getAllPickaxes();
    }


    public Pickaxe getPickaxeById(Integer id) {
        return pickaxeDAO.getPickaxeById(id).orElse(null);
    }

    public void addPickaxe(Pickaxe pickaxe) {
        pickaxeDAO.addPickaxe(pickaxe);
    }

    public Pickaxe getStartingPickaxe() {
        Pickaxe aPickaxe = getAllPickaxes()
                .stream()
                .filter(pickaxe -> pickaxe.getItemName().equalsIgnoreCase("Wooden pickaxe"))
                .findFirst()
                .get();
        Pickaxe newPickaxe = new Pickaxe();
        newPickaxe.setItemName(aPickaxe.getItemName());
        newPickaxe.setCondition(aPickaxe.getCondition());
        newPickaxe.setStrength(aPickaxe.getStrength());
        pickaxeDAO.addPickaxe(newPickaxe);
        return newPickaxe;
    }
}
