package com.desafios.spring.exceptions;

public class NoBuyerException extends RuntimeException {

    public String getMessage() {
        return "El usuario no existe";
    }
}
