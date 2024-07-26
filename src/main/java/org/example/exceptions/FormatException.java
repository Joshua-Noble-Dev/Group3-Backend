package org.example.exceptions;

public class FormatException extends Throwable {
    public FormatException(final Entity entity) {
        super(entity.getEntity() + " is in the wrong information format");
    }
}
