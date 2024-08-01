package org.example.exceptions;

public class FailedToCreateException extends Throwable {
    public FailedToCreateException(final Entity entity) {
        super(entity.getEntity() + " Failed to create");

    public FailedToCreateException(Entity entity) {
        super(entity.getEntity() + " failed to create");
    }
}