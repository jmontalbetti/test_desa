package com.desafios.spring.exceptions;

public class NoSellerException extends RuntimeException {

    public String getMessage() {
        return "El vendedor no existe";
    }
}