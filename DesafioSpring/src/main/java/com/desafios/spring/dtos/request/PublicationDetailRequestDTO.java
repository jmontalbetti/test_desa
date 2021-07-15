package com.desafios.spring.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class PublicationDetailRequestDTO {
    @NotNull(message = "El id del producto no puede ser nulo")
    @Min(value = 1, message = "El id del producto no puede ser menor a 1")
    private Integer product_id;

    @NotEmpty(message = "El nombre del producto no puede estar vacío")
    private String productName;

    @NotEmpty(message = "El tipo de producto no puede estar vacío")
    private String type;

    @NotEmpty(message = "La marca del producto no puede estar vacía")
    private String brand;

    @NotEmpty(message = "El color del producto no puede estar vacío")
    private String color;

    @NotEmpty(message = "Las notas del producto no pueden estar vacías")
    private String notes;
}
