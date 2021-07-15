package com.desafios.spring.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyersCountResponseDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;
}
