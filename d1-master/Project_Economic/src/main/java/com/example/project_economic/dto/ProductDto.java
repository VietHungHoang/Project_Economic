package com.example.project_economic.dto;

import com.example.project_economic.entity.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double costPrice;
    private Double salePrice;
    private Integer currentQuantity;
    private Integer Likes;
    private String image;
    private Long  categoryId;
    private Boolean is_deteted;
    private Boolean is_actived;
}
