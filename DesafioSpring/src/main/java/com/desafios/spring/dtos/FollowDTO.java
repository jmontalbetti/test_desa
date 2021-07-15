package com.desafios.spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FollowDTO {
    private UserDTO follower;
    private SellerDTO followed;
}
