package com.example.project_economic.dto;

import com.example.project_economic.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryCartDto {
    private Long id;
    private ProductResponse productResponse;
    private Boolean Received=false;

    private LocalDateTime BoughtAt;

    private Integer quantity;
    private Double sumTotalInCartHistory(){
        return this.quantity*this.productResponse.getCostPrice();
    }

}
