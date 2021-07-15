package com.desafios.spring.repositories;

import com.desafios.spring.dtos.*;

import java.util.Map;

public interface SocialMeliRepository {
    Map<Integer, FollowDTO> getBuyerList();
    Map<Integer, ProductDTO> getPublicationList();
    void loadFollowsDatabase();
    void loadProductsDatabase();
    void loadProductsWithPromoDatabase();

    void setFollow(FollowDTO followDTO);
    UserDTO getBuyer(Integer follower);
    SellerDTO getSeller(Integer followed);
    void setUnfollow(Integer id);

    ProductDTO setPublication(ProductDTO product);
}
