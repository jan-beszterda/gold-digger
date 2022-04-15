package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MineService {
    private MineDAO mineDAO;

    public MineService(MineDAO mineDAO) {
        this.mineDAO = mineDAO;
    }

    public List<Mine> getAllMines() {
        return (List<Mine>) mineDAO.findAllMines();
    }

    public Mine getMineById(Integer id) {
        return mineDAO.findMineByID(id).orElse(null);
    }

    public void removeMine(Integer id) {
        mineDAO.deleteMine(id);
    }

    public Mine addMine(Mine mine) {
        return mineDAO.saveMine(mine);
    }
}
