package com.desafios.spring.dtos.request;

import com.desafios.spring.handlers.ProductHandler;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Validated
public class PublicationRequestDTO {
    @NotNull(message = "El id de usuario no puede ser nulo")
    private Integer userId;

    @NotEmpty(message = "La fecha no puede estar vacía")
    private String date;

    @NotNull(message = "El detalle del producto no puede ser nulo")
    @Valid
    private PublicationDetailRequestDTO detail;

    @NotNull(message = "La categoría no puede ser nula")
    @Min(value = 1, message = "La categoría no puede ser menor a 1")
    @Max(value = 1000000, message = "La categoría no puede ser meyor a 1000000")
    private Integer category;

    @NotNull(message = "El precio no puede ser nulo")
    @Digits(integer = 10, fraction = 2, message = "El formato debe ser 9999999999.99")
    @DecimalMin(value = "0.01", message = "El valor mínimo debe ser 0.01")
    private Double price;

    public PublicationRequestDTO() {
        ProductHandler.validateProductDate(this.getDate());
    }
}
