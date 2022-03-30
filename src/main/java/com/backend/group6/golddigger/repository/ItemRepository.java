package com.backend.group6.golddigger.repository;

import com.backend.group6.golddigger.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
