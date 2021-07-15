package com.desafios.spring.dtos;

import com.desafios.spring.dtos.request.PublicationPromoRequestDTO;
import com.desafios.spring.dtos.request.PublicationRequestDTO;
import com.desafios.spring.handlers.ProductHandler;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private Integer user_id;
    private Date date;
    private ProductDetailDTO detail;
    private Integer category;
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Boolean hasPromo;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double discount;

    public ProductDTO(Integer id, Integer user_id, Date date, ProductDetailDTO detail, Integer category, Double price) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public ProductDTO(PublicationRequestDTO publication) {
        this.user_id = publication.getUserId();
        this.date = ProductHandler.validateProductDate(publication.getDate());
        this.category = publication.getCategory();
        this.price = publication.getPrice();
        this.detail = new ProductDetailDTO(publication.getDetail());
    }

    public ProductDTO(PublicationPromoRequestDTO publication) {
        this.user_id = publication.getUserId();
        this.date = ProductHandler.validateProductDate(publication.getDate());
        this.category = publication.getCategory();
        this.price = publication.getPrice();
        this.detail = new ProductDetailDTO(publication.getDetail());
        this.hasPromo = publication.getHasPromo();
        this.discount = publication.getDiscount();
    }
}
