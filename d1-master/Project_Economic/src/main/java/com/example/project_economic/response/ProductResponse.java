package com.example.project_economic.response;

import com.example.project_economic.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Long costPrice;
    private Long salePrice;
    private Integer currentQuantity;
    private Integer Likes;
    private String image;
    private String image_url;
    private String image_type;
    private CategoryEntity categoryEntity;
    private Boolean is_deleted;
    private Boolean is_actived;
    private Integer sellerId;

    public String getSalePriceFormat(){
        String formattedNumber = this.salePrice.toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < formattedNumber.length(); i++) {
            char currentChar = formattedNumber.charAt(i);
            if (Character.isDigit(currentChar) && i > 0 && (formattedNumber.length() - i) % 3 == 0) result.append('.');
            result.append(currentChar);
        }
        return result.toString() + '₫';
    }

    public String getCostPriceFormat(){
        String formattedNumber = this.costPrice.toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < formattedNumber.length(); i++) {
            char currentChar = formattedNumber.charAt(i);
            if (Character.isDigit(currentChar) && i > 0 && (formattedNumber.length() - i) % 3 == 0) result.append('.');
            result.append(currentChar);
        }
        return result.toString() + '₫';
    }
}
