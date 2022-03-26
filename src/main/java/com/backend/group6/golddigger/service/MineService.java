package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MineService {

    MineDAO mineDAO;

    public MineService(MineDAO mineDAO) {
        this.mineDAO = mineDAO;
    }

    public List<Mine> getAllMines() {
        return mineDAO.getAllMines();
    }

    public Mine getMineById(UUID id) {
        return mineDAO.getMineByID(id).orElse(null);
    }

    public void removeMine(UUID id) {
        mineDAO.deleteMine(id);
    }
}
