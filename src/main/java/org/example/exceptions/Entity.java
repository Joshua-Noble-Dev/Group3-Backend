package org.example.exceptions;

public enum Entity {

    JOBROLE ("Job Role");

    private final String entity;

    Entity(String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
