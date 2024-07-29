package org.example.exceptions;

public enum Entity {
    ROLEDETAIL("ID");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}