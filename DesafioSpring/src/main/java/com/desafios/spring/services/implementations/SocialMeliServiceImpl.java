package com.desafios.spring.services.implementations;

import com.desafios.spring.dtos.*;
import com.desafios.spring.dtos.request.PublicationRequestDTO;
import com.desafios.spring.dtos.response.*;
import com.desafios.spring.exceptions.*;
import com.desafios.spring.repositories.implementations.SocialMeliRepositoryImpl;
import com.desafios.spring.services.SocialMeliService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class SocialMeliServiceImpl implements SocialMeliService {
    SocialMeliRepositoryImpl socialMeliRepository;

    public SocialMeliServiceImpl(SocialMeliRepositoryImpl socialMeliRepository) {this.socialMeliRepository = socialMeliRepository; }

    @Override
    public void loadFollowsDatabase() { this.socialMeliRepository.loadFollowsDatabase(); }
    @Override
    public void loadProductsDatabase() { this.socialMeliRepository.loadProductsDatabase(); }
    @Override
    public void loadProductsWithPromoDatabase() { this.socialMeliRepository.loadProductsWithPromoDatabase(); }

    //0001
    @Override
    public FollowDTO setFollow(Integer buyerId, Integer sellerId) {
        //TODO ver la forma de obtener el siguiente id porque de la forma actual puede repetirse
        UserDTO buyer = validateAndGetBuyer(buyerId);
        SellerDTO seller = validateAndGetSeller(sellerId);
        FollowDTO followDTO = new FollowDTO(buyer, seller);
        List<FollowDTO> followsList = socialMeliRepository.getBuyerList().values().stream().
                filter(f -> f.getFollower().equals(buyer) && f.getFollowed().equals(seller)).
                collect(Collectors.toList());
        if (followsList.size() > 0) throw new RelationExistsException();
        socialMeliRepository.setFollow(followDTO);
        return followDTO;
    }
    private UserDTO validateAndGetBuyer(Integer buyerId) {
        UserDTO buyer = socialMeliRepository.getBuyer(buyerId);
        if (Objects.isNull(buyer)) throw new NoBuyerException();
        return buyer;
    }
    private SellerDTO validateAndGetSeller(Integer sellerId) {
        SellerDTO seller = socialMeliRepository.getSeller(sellerId);
        if (Objects.isNull(seller)) throw new NoSellerException();
        return seller;
    }
    //0002
    @Override
    public BuyersCountResponseDTO getBuyersCount(Integer sellerId) {
        SellerDTO seller = validateAndGetSeller(sellerId);
        List<FollowDTO> followDTO = socialMeliRepository.getBuyerList().values().stream().
                filter(f -> f.getFollowed().equals(seller)).collect(Collectors.toList());
        return new BuyersCountResponseDTO(seller.getUserId(), seller.getUserName(), followDTO.size());
    }
    //0003 & 0008
    @Override
    public BuyersListResponseDTO getBuyersList(Integer sellerId, String order) {
        SellerDTO seller = validateAndGetSeller(sellerId);
        List<UserDTO> buyersList = getBuyers(seller);
        switch (order) {
            case "name_asc":
                buyersList.sort(Comparator.comparing(UserDTO::getUserName));
                break;
            case "name_desc":
                buyersList.sort(Comparator.comparing(UserDTO::getUserName).reversed());
                break;
            default:
                throw new WrongOrderException();
        }
        return new BuyersListResponseDTO(seller.getUserId(), seller.getUserName(), buyersList);
    }
    private List<UserDTO> getBuyers(SellerDTO followedDTO) {
        List<FollowDTO> followsList = socialMeliRepository.getBuyerList().values().stream().
                filter(f -> f.getFollowed().equals(followedDTO)).collect(Collectors.toList());
        List<UserDTO> buyers = new ArrayList<>();
        followsList.forEach(f -> buyers.add(f.getFollower()));
        return buyers;
    }
    //0004 & 0008
    @Override
    public SellersListResponseDTO getSellersList(Integer buyerId, String order) {
        UserDTO buyer = validateAndGetBuyer(buyerId);
        List<SellerDTO> sellersList = getSellers(buyer);
        switch (order) {
            case "name_asc":
                sellersList.sort(Comparator.comparing(SellerDTO::getUserName));
                break;
            case "name_desc":
                sellersList.sort(Comparator.comparing(SellerDTO::getUserName).reversed());
                break;
            default:
                throw new WrongOrderException();
        }
        return new SellersListResponseDTO(buyer.getUserId(), buyer.getUserName(), sellersList);
    }
    private List<SellerDTO> getSellers(UserDTO followerDTO) {
        List<FollowDTO> followsList = socialMeliRepository.getBuyerList().values().stream().
                filter(f -> f.getFollower().equals(followerDTO)).collect(Collectors.toList());
        List<SellerDTO> sellersList = new ArrayList<>();
        followsList.forEach(f -> sellersList.add(f.getFollowed()));
        return sellersList;
    }
    //0007
    @Override
    public BuyersListResponseDTO setUnfollow(Integer buyerId, Integer sellerId) {
        UserDTO buyer = validateAndGetBuyer(buyerId);
        SellerDTO seller = validateAndGetSeller(sellerId);
        FollowDTO follow = new FollowDTO(buyer, seller);
        List<Map.Entry<Integer, FollowDTO>> followsListMap = socialMeliRepository.getBuyerList().entrySet().
                stream().filter(f -> f.getValue().equals(follow)).collect(Collectors.toList());
        if (followsListMap.size() == 0) throw new RelationDontExistsException();
        socialMeliRepository.setUnfollow(followsListMap.get(0).getKey());
        return getBuyersList(buyerId, "name_asc");
    }

    //0005 && 0010
    @Override
    public ProductDTO setPublication(PublicationRequestDTO publication) {
        return socialMeliRepository.setPublication(new ProductDTO(publication));
    }
    //0006 && 0009
    @Override
    public ProductsListResponseDTO getProductsList(Integer buyerId, String order) {
        UserDTO buyer = validateAndGetBuyer(buyerId);
        List<ProductDTO> productsList = getProducts(buyer);
        switch (order) {
            case "date_asc":
                productsList.sort(Comparator.comparing(ProductDTO::getDate));
                break;
            case "date_desc":
                productsList.sort(Comparator.comparing(ProductDTO::getDate).reversed());
                break;
            default:
                throw new WrongOrderException();
        }
        return new ProductsListResponseDTO(buyer.getUserId(), productsList);
    }
    private List<ProductDTO> getProducts(UserDTO buyer) {
        List<SellerDTO> sellersList = getSellers(buyer);
        List<ProductDTO> productsList = new ArrayList<>();
        sellersList.forEach(f -> {
            List<ProductDTO> products = socialMeliRepository.getPublicationList().values().stream().
                    filter(p -> p.getUser_id().equals(f.getUserId())).collect(Collectors.toList());
            productsList.addAll(products);
            productsList.sort(Comparator.comparing(ProductDTO::getDate).reversed());
        });
        return productsList;
    }
    //0011
    @Override
    public ProductsCountResponseDTO getProductsCount(Integer sellerId) {
        SellerDTO seller = validateAndGetSeller(sellerId);
        List<ProductDTO> productsList = socialMeliRepository.getPublicationList().values().stream().
                filter(f -> f.getUser_id().equals(sellerId) &
                        Objects.nonNull(f.getHasPromo()) &
                        Objects.nonNull(f.getDiscount())).collect(Collectors.toList());
        return new ProductsCountResponseDTO(seller.getUserId(), seller.getUserName(), productsList.size());
    }
    //0012
    @Override
    public ProductsListResponseDTO getProductsWithPromoList(Integer sellerId) {
        SellerDTO seller = validateAndGetSeller(sellerId);
        List<ProductDTO> productsList = socialMeliRepository.getPublicationList().values().stream().
                filter(f -> f.getUser_id().equals(sellerId) &
                        Objects.nonNull(f.getHasPromo()) &
                        Objects.nonNull(f.getDiscount())).collect(Collectors.toList());
        return new ProductsListResponseDTO(seller.getUserId(), productsList);
    }
}
