package com.desafios.spring.handlers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ProductHandler {
    public static Date validateProductDate(String productDate) {
        Date productDateDTO = null;
        try {
            if (Objects.nonNull(productDate))
                productDateDTO = new SimpleDateFormat("dd-MM-yyyy").parse(productDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return productDateDTO;
    }
}
