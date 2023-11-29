package com.example.hibernatebuoi38.service.impl;

import com.example.hibernatebuoi38.dao.IColorDAO;
import com.example.hibernatebuoi38.dto.ColorDTO;
import com.example.hibernatebuoi38.entity.ColorEntity;
import com.example.hibernatebuoi38.service.IColorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements IColorService {

    private final IColorDAO iColorDAO;

    public ColorServiceImpl(IColorDAO iColorDAO) {
        this.iColorDAO = iColorDAO;
    }

    @Override
    public void save(ColorDTO colorDTO) {
        this.iColorDAO.insert(dto_to_entity(colorDTO));
    }

    @Override
    public List<ColorDTO> getAll() {
        return this.iColorDAO.findAll().stream().map(entity->{
            return entity_to_dto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public ColorDTO update(ColorDTO colorDTO) {
        ColorEntity colorEntity=this.iColorDAO.update(dto_to_entity(colorDTO));
        return entity_to_dto(colorEntity);
    }

    @Override
    public ColorDTO findById(Long id) {
        ColorEntity colorEntity=this.iColorDAO.findById(id);
        return entity_to_dto(colorEntity);
    }

    @Override
    public void delete(Long id) {
        try{
            this.iColorDAO.delete(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ColorEntity dto_to_entity(ColorDTO colorDTO){
        ColorEntity colorEntity=ColorEntity.builder()
                .id(colorDTO.getId())
                .name(colorDTO.getName())
                .code(colorDTO.getCode())
                .build();
        return colorEntity;
    }

    public ColorDTO entity_to_dto(ColorEntity colorEntity){
        ColorDTO colorDTO=ColorDTO.builder()
                .id(colorEntity.getId())
                .name(colorEntity.getName())
                .code(colorEntity.getCode())
                .build();
        return colorDTO;
    }
}
