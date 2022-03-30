package com.backend.group6.golddigger.repository;

import com.backend.group6.golddigger.model.FoodItem;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodItem, Integer> {
}
