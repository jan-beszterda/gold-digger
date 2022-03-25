package com.backend.group6.golddigger.model;

import java.util.UUID;

public class Item {

    private UUID id;
    private String name;

    public Item(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
