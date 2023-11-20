package com.example.project_economic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String description;
    private Double costPrice;
    private Double salePrice;
    private Integer currentQuantity;
    private Integer Likes;
    private String image;
    private String image_type;

    @Lob
    @Column(name = "data",columnDefinition = "LONGBLOB")
    private byte[] data;

    @ManyToOne(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private CategoryEntity categoryEntity;
    private Boolean is_deteted;
    private Boolean is_actived;

}
