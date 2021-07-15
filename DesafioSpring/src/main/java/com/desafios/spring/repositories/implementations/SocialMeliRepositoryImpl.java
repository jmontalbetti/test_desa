package com.desafios.spring.repositories.implementations;

import com.desafios.spring.dtos.*;
import com.desafios.spring.handlers.ProductHandler;
import com.desafios.spring.repositories.SocialMeliRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SocialMeliRepositoryImpl implements SocialMeliRepository {
    private final Map<Integer, UserDTO> userDatabase = new HashMap<>();
    private final Map<Integer, SellerDTO> sellerDatabase = new HashMap<>();
    private final Map<Integer, FollowDTO> followDatabase = new HashMap<>();
    private final Map<Integer, ProductDTO> productDatabase = new HashMap<>();
    private final AtomicInteger atomicIntegerFollow = new AtomicInteger(1);
    private final AtomicInteger atomicIntegerProduct = new AtomicInteger(1);

    public SocialMeliRepositoryImpl() {
        loadUsersDatabase();
        loadSellersDatabase();
    }

    private void loadUsersDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/users.json");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mapUsersObject(file);
    }

    private void mapUsersObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDTO>> typeReference = new TypeReference<>(){};
        try {
            List<UserDTO> userListDTO = objectMapper.readValue(file, typeReference);
            userListDTO.forEach(userDTO -> userDatabase.put(userDTO.getUserId(), userDTO));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSellersDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:static/sellers.json");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mapSellersObject(file);
    }

    private void mapSellersObject(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<SellerDTO>> typeReference = new TypeReference<>(){};
        try {
            List<SellerDTO> sellerListDTO = objectMapper.readValue(file, typeReference);
            sellerListDTO.forEach(sellerDTO -> sellerDatabase.put(sellerDTO.getUserId(), sellerDTO));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFollowsDatabase() {
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(1),sellerDatabase.get(1)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(1),sellerDatabase.get(3)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(2),sellerDatabase.get(5)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(3),sellerDatabase.get(2)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(3),sellerDatabase.get(5)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(4),sellerDatabase.get(3)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(4),sellerDatabase.get(4)));
        followDatabase.put(atomicIntegerFollow.getAndIncrement(), new FollowDTO(userDatabase.get(4),sellerDatabase.get(5)));
    }

    @Override
    public void loadProductsDatabase() {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(1, "Silla Gamer", "Gamer", "Racer", "Red & Black", "Special Edition");
        Date date = ProductHandler.validateProductDate("28-06-2021");
        ProductDTO productDTO = new ProductDTO(atomicIntegerProduct.get(), 1, date, productDetailDTO, 100, 1500.50);
        productDatabase.put(atomicIntegerProduct.getAndIncrement(), productDTO);

        productDetailDTO = new ProductDetailDTO(2, "Silla Jardin", "Jardin", "Chairs SA", "Black", "Antioxido");
        date = ProductHandler.validateProductDate("07-05-2021");
        productDTO = new ProductDTO(atomicIntegerProduct.get(), 1, date, productDetailDTO, 100, 950.0);
        productDatabase.put(atomicIntegerProduct.getAndIncrement(), productDTO);

        productDetailDTO = new ProductDetailDTO(3, "Silla Plastica", "Versatil", "Colombraro", "White", "Reforzada");
        date = ProductHandler.validateProductDate("03-07-2021");
        productDTO = new ProductDTO(atomicIntegerProduct.get(), 1, date, productDetailDTO, 100, 375.99);
        productDatabase.put(atomicIntegerProduct.getAndIncrement(), productDTO);
    }

    @Override
    public void loadProductsWithPromoDatabase() {
        ProductDetailDTO productDetailDTO = new ProductDetailDTO(2, "Silla Gamer c/promo", "Gamer", "Racer", "Red & Black", "Special Edition");
        Date date = ProductHandler.validateProductDate("05-07-2021");
        ProductDTO productDTO = new ProductDTO(atomicIntegerProduct.get(), 1, date, productDetailDTO, 100, 1500.50, true, 50.0);
        productDatabase.put(atomicIntegerProduct.getAndIncrement(), productDTO);

        productDetailDTO = new ProductDetailDTO(2, "Silla Jardin c/promo", "Jardin", "Chairs SA", "Black", "Antioxido");
        date = ProductHandler.validateProductDate("20-05-2021");
        productDTO = new ProductDTO(atomicIntegerProduct.get(), 1, date, productDetailDTO, 100, 950.0, true, 50.0);
        productDatabase.put(atomicIntegerProduct.getAndIncrement(), productDTO);
    }

    @Override
    public Map<Integer, FollowDTO> getBuyerList() {
        return followDatabase;
    }

    @Override
    public Map<Integer, ProductDTO> getPublicationList() {
        return productDatabase;
    }

    @Override
    public void setFollow(FollowDTO followDTO) { followDatabase.put(atomicIntegerFollow.getAndIncrement(), followDTO); }

    @Override
    public UserDTO getBuyer(Integer follower) {
        return userDatabase.get(follower);
    }

    @Override
    public SellerDTO getSeller(Integer followed) {
        return sellerDatabase.get(followed);
    }

    @Override
    public void setUnfollow(Integer id) {
        followDatabase.remove(id);
    }

    @Override
    public ProductDTO setPublication(ProductDTO product) {
        product.setId(atomicIntegerProduct.getAndIncrement());
        productDatabase.put(product.getId(), product);
        return product;
    }
}
