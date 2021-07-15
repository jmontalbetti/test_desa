package com.desafios.spring.exceptions;

public class RelationExistsException extends RuntimeException {

    public String getMessage() {
        return "La relación ya existe";
    }
}
