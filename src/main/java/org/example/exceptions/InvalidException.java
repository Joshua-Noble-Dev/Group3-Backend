package org.example.exceptions;

public class InvalidException extends Throwable {
    public InvalidException(final Entity entity) {
        super(entity.getEntity() + " is invalid");
    }
}
