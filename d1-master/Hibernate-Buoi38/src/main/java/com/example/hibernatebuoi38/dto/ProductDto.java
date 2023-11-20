package com.example.hibernatebuoi38.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.ls.LSException;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    private String name;

    private String code;

    private Integer quantity;

    private Double price;

    private String description;

    private Double status;
    private Instant createDate=Instant.now();

    private String createBy="admin";

    private Instant modifyDate=Instant.now();

    private String modifyBy="admin";

    private Long categoryId;

    private String categoryName;

    private List<Long> sizeId;

    private List<String> sizeName;

    private List<Long>colorId;

    private List<String> colorCode;
}
