package com.desafios.spring.dtos.response;

import com.desafios.spring.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyersListResponseDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followers;
}
