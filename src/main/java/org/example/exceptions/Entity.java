package org.example.exceptions;

public enum Entity {

    USER("User"), ROLE("Role");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
