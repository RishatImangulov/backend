package com.richard.backend.exception;


public class NotUniqueFieldException extends RuntimeException {

    public NotUniqueFieldException(String fieldName, String entityName) {
        super("Field %s must be uniq for %s".formatted(fieldName, entityName));
    }


}
