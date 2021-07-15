package com.desafios.spring.controllers;

import com.desafios.spring.dtos.FollowDTO;
import com.desafios.spring.dtos.UserDTO;
import com.desafios.spring.dtos.response.SellersListResponseDTO;
import com.desafios.spring.dtos.response.BuyersCountResponseDTO;
import com.desafios.spring.dtos.response.BuyersListResponseDTO;
import com.desafios.spring.services.SocialMeliService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UsersController {
    private final SocialMeliService socialMeliService;

    public UsersController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @GetMapping("/loadFollows")
    public void loadFollowsDatabase() { this.socialMeliService.loadFollowsDatabase(); }

    //0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    @ResponseBody
    public ResponseEntity<FollowDTO> create(@Valid @PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(socialMeliService.setFollow(userId, userIdToFollow), HttpStatus.OK);
    }

    //0002
    @GetMapping("/{userId}/followers/count")
    @ResponseBody
    public ResponseEntity<BuyersCountResponseDTO> followersCount(@PathVariable Integer userId) {
        return new ResponseEntity<>(socialMeliService.getBuyersCount(userId), HttpStatus.OK);
    }

    //0003 & 0008
    @GetMapping(value = "/{UserID}/followers/list")
    @ResponseBody
    public ResponseEntity<BuyersListResponseDTO> followersList(@PathVariable Integer UserID, @RequestParam(required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(socialMeliService.getBuyersList(UserID, order), HttpStatus.OK);
    }

    //0004 & 0008
    @GetMapping(value = "/{UserID}/followed/list")
    @ResponseBody
    public ResponseEntity<SellersListResponseDTO> followedList(@PathVariable Integer UserID, @RequestParam(required = false, defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(socialMeliService.getSellersList(UserID, order), HttpStatus.OK);
    }

    //0007
    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseBody
    public ResponseEntity<BuyersListResponseDTO> unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        return new ResponseEntity<>(socialMeliService.setUnfollow(userId, userIdToUnfollow), HttpStatus.OK);
    }

}
