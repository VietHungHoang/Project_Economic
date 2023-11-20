package com.example.hibernatebuoi38.service;

import com.example.hibernatebuoi38.dto.SizeDto;

import java.util.List;

public interface ISizeService {
    void insert(SizeDto sizeDto);
    List<SizeDto>getAll();

    SizeDto getById(Long id);

    SizeDto update(SizeDto sizeDto);

    void delete(Long id);
}
