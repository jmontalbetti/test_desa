package com.desafios.spring.services;

import com.desafios.spring.dtos.FollowDTO;
import com.desafios.spring.dtos.ProductDTO;
import com.desafios.spring.dtos.UserDTO;
import com.desafios.spring.dtos.request.PublicationRequestDTO;
import com.desafios.spring.dtos.response.*;

public interface SocialMeliService {
    void loadFollowsDatabase();
    void loadProductsDatabase();
    void loadProductsWithPromoDatabase();

    //0001
    FollowDTO setFollow(Integer buyerId, Integer sellerId);
    //0002
    BuyersCountResponseDTO getBuyersCount(Integer sellerId);
    //0003 & 0008
    BuyersListResponseDTO getBuyersList(Integer sellerId, String order);
    //0004 & 0008
    SellersListResponseDTO getSellersList(Integer buyerId, String order);
    //0007
    BuyersListResponseDTO setUnfollow(Integer buyerId, Integer followed);

    //0005 & 0010
    ProductDTO setPublication(PublicationRequestDTO publication);
    //0006 & 0009
    ProductsListResponseDTO getProductsList(Integer buyerId, String order);
    //0011
    ProductsCountResponseDTO getProductsCount(Integer sellerId);
    //0012
    ProductsListResponseDTO getProductsWithPromoList(Integer sellerId);
}
