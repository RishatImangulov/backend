package com.richard.backend.exception;

public class FieldIsBlank extends RuntimeException {
    public FieldIsBlank(String field, String entity) {
        super("Field %s for entity %s can't be blank".formatted(field, entity));
    }
}
