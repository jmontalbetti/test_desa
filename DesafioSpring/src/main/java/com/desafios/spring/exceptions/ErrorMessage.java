package com.desafios.spring.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;

@Getter
public class ErrorMessage{

    private Integer status;
    private String error;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, String> message;

    public ErrorMessage(Integer status, String error, Map<String, String> message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}