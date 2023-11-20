package com.example.hibernatebuoi38.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CategoryEntity categoryEntity;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<SizeEntity>sizeEntities=new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_color",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ColorEntity> colorEntities=new HashSet<>();

}
