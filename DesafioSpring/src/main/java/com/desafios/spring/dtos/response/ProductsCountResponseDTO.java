package com.desafios.spring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;
}