package com.desafios.spring.controllers;

import com.desafios.spring.dtos.ProductDTO;
import com.desafios.spring.dtos.request.PublicationPromoRequestDTO;
import com.desafios.spring.dtos.request.PublicationRequestDTO;
import com.desafios.spring.dtos.response.ProductsCountResponseDTO;
import com.desafios.spring.dtos.response.ProductsListResponseDTO;
import com.desafios.spring.services.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final SocialMeliService socialMeliService;

    public ProductsController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @GetMapping("/loadProducts")
    public void loadProductsDatabase() { this.socialMeliService.loadProductsDatabase(); }

    @GetMapping("/loadProductsWithPromo")
    public void loadProductsWithPromoDatabase() { this.socialMeliService.loadProductsWithPromoDatabase(); }

    //0005
    @PostMapping("/newpost")
    @ResponseBody
    public ResponseEntity<ProductDTO> createPost(@Valid @RequestBody PublicationRequestDTO publication) {
        return new ResponseEntity<>(socialMeliService.setPublication(publication), HttpStatus.OK);
    }

    //0006 & 0009
    @GetMapping("/followed/{userId}/list")
    @ResponseBody
    public ResponseEntity<ProductsListResponseDTO> productsList(@PathVariable Integer userId, @RequestParam(required = false, defaultValue = "date_desc") String order) {
        return new ResponseEntity<>(socialMeliService.getProductsList(userId, order), HttpStatus.OK);
    }

    //0010
    @PostMapping("/newpromopost")
    @ResponseBody
    public ResponseEntity<ProductDTO> followersListOrdered(@Valid @RequestBody PublicationPromoRequestDTO publication) {
        return new ResponseEntity<>(socialMeliService.setPublication(publication), HttpStatus.OK);
    }

    //0011
    @GetMapping("/{userId}/countPromo")
    @ResponseBody
    public ResponseEntity<ProductsCountResponseDTO> getProductsPromoCount(@PathVariable Integer userId) {
        return new ResponseEntity<>(socialMeliService.getProductsCount(userId), HttpStatus.OK);
    }

    //0012
    @GetMapping("/{userId}/list")
    @ResponseBody
    public ResponseEntity<ProductsListResponseDTO> productsListByUser(@PathVariable Integer userId) {
        return new ResponseEntity<>(socialMeliService.getProductsWithPromoList(userId), HttpStatus.OK);
    }
}
