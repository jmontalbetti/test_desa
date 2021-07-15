package com.desafios.spring.dtos.response;

import com.desafios.spring.dtos.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsListResponseDTO {
    private Integer userId;
    private List<ProductDTO> posts;
}
