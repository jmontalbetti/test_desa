package com.desafios.spring.exceptions;

public class WrongOrderException extends RuntimeException {

    public String getMessage() {
        return "El orden no existe";
    }
}
