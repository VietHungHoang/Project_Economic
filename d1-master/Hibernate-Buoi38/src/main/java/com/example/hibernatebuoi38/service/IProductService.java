package com.example.hibernatebuoi38.service;

import com.example.hibernatebuoi38.dto.ProductDto;

import java.util.List;

public interface IProductService {
    void insert(ProductDto productDto);

    List<ProductDto>getAll();

    ProductDto findById(Long id);

    ProductDto update(ProductDto productDto);

    void delete(Long id);

    List<ProductDto> findByCategory(Long categoryId);

    List<ProductDto>findBySize(Long sizeId);
}
