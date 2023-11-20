package com.example.hibernatebuoi38.dao;

import com.example.hibernatebuoi38.entity.CategoryEntity;
import com.example.hibernatebuoi38.entity.ProductEntity;

import java.util.List;

public interface IProductDao extends IGenericDAO<ProductEntity>{
    List<ProductEntity> findProductByCategory(CategoryEntity categoryId);
}
