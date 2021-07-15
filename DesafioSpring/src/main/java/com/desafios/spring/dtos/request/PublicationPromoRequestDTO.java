package com.desafios.spring.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class PublicationPromoRequestDTO extends PublicationRequestDTO {
    @NotNull(message = "La opción 'Tiene promo' es nula")
    private Boolean hasPromo;

    @NotNull(message = "El precio no puede ser nulo")
    @Digits(integer = 10, fraction = 2, message = "El formato debe ser 9999999999.99")
    @DecimalMin(value = "0.01", message = "El valor mínimo debe ser 0.01")
    private Double discount;
}
