package com.example.project_economic.response;

import com.example.project_economic.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double costPrice;
    private Double salePrice;
    private Integer currentQuantity;
    private Integer Likes;
    private String image;
    private String image_url;
    private String image_type;
    private CategoryEntity categoryEntity;
    private Boolean is_deleted;
    private Boolean is_actived;
}
