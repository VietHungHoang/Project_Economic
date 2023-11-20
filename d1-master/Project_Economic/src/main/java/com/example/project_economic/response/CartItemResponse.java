package com.example.project_economic.response;

import com.example.project_economic.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private Long id;
    private ProductResponse productResponse;
    private UserEntity user;
    private int quantity;
    public Double totalInCartItem(){
        return this.quantity*this.productResponse.getCostPrice();
    }
}
