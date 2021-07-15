package com.desafios.spring.exceptions;

public class RelationDontExistsException extends RuntimeException{

    public String getMessage() {
        return "La relación no existe";
    }
}
