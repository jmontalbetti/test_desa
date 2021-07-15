package com.desafios.spring.dtos.response;

import com.desafios.spring.dtos.SellerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellersListResponseDTO {
    private Integer userId;
    private String userName;
    private List<SellerDTO> followed;
}
