package com.backend.group6.golddigger.repository;

import com.backend.group6.golddigger.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
