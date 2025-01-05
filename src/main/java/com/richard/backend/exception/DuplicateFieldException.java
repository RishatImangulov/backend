package com.richard.backend.exception;


public class DuplicateFieldException extends RuntimeException {

    public DuplicateFieldException(String fieldName, String entityName) {
        super("Field %s must be uniq for %s".formatted(fieldName, entityName));
    }


}
