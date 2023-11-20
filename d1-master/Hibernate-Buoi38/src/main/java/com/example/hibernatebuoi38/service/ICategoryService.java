package com.example.hibernatebuoi38.service;

import com.example.hibernatebuoi38.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    void save(CategoryDTO categoryDTO);
    List<CategoryDTO> getAll();

    CategoryDTO update(CategoryDTO categoryDTO);

    CategoryDTO findById(Long id);

    void delete(Long id);
}
