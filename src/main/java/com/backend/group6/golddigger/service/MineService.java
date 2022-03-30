package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class MineService {

    MineDAO mineDAO;
    Mine mine;

    public MineService(MineDAO mineDAO) {
        this.mineDAO = mineDAO;
    }

    public List<Mine> getAllMines() {
        return (List<Mine>) mineDAO.getAllMines();
    }

    public Mine getMineById(Integer id) {
        return mineDAO.getMineByID(id).orElse(null);
    }

    public void removeMine(Integer id) {
        mineDAO.deleteMine(id);
    }

    public void createRandomMine() {
        Random r = new Random();
        Mine mineToCreate = new Mine();
        Optional<Mine> mineToDuplicate = getAllMines()
                .stream()
                .findAny();
        mineToDuplicate.ifPresent(mine -> mineToCreate.setName(mine.getName()));
        mineToCreate.setDifficulty(r.nextDouble());
        mineToCreate.setTotalGold(r.nextDouble(100.0, 1001.00));
        mineDAO.createRandomMine(mineToCreate);
    }

}
