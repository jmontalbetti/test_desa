package com.desafios.spring.dtos;

import com.desafios.spring.dtos.request.PublicationDetailRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {
    private Integer product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public ProductDetailDTO(PublicationDetailRequestDTO detail) {
        this.product_id = detail.getProduct_id();
        this.productName = detail.getProductName();
        this.type = detail.getType();
        this.brand = detail.getBrand();
        this.color = detail.getColor();
        this.notes = detail.getNotes();
    }
}
