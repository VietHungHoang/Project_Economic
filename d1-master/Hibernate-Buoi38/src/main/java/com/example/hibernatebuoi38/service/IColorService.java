package com.example.hibernatebuoi38.service;

import com.example.hibernatebuoi38.dto.ColorDTO;

import java.util.List;

public interface IColorService {
    void save(ColorDTO colorDTO);

    List<ColorDTO> getAll();

    ColorDTO update(ColorDTO colorDTO);

    ColorDTO findById(Long id);

    void delete(Long id);
}
