package com.backend.group6.golddigger.service;

import com.backend.group6.golddigger.dao.MineDAO;
import com.backend.group6.golddigger.model.Mine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MineService {

    MineDAO mineDAO;

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
}
