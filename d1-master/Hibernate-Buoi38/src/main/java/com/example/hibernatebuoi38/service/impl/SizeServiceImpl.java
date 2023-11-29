package com.example.hibernatebuoi38.service.impl;

import com.example.hibernatebuoi38.dao.ISizeDao;
import com.example.hibernatebuoi38.dto.SizeDto;
import com.example.hibernatebuoi38.entity.SizeEntity;
import com.example.hibernatebuoi38.service.ISizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements ISizeService {
    private final ISizeDao iSizeDao;

    public SizeServiceImpl(ISizeDao iSizeDao) {
        this.iSizeDao = iSizeDao;
    }

    @Override
    public void insert(SizeDto sizeDto) {
        this.iSizeDao.save(dto_to_entity(sizeDto));
    }


    @Override
    public List<SizeDto> getAll() {
        return this.iSizeDao.findAll().stream().map(entity->{
            return entity_to_dto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public SizeDto getById(Long id) {
        SizeEntity sizeEntity=this.iSizeDao.findById(id);
        return this.entity_to_dto(sizeEntity);
    }

    @Override
    public SizeDto update(SizeDto sizeDto) {
        SizeEntity sizeEntity=this.iSizeDao.update(dto_to_entity(sizeDto));
        return entity_to_dto(sizeEntity);
    }

    @Override
    public void delete(Long id) {
        try{
            this.iSizeDao.delete(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public SizeDto entity_to_dto(SizeEntity sizeEntity){
        SizeDto sizeDto=SizeDto.builder()
                .id(sizeEntity.getId())
                .name(sizeEntity.getName())
                .code(sizeEntity.getCode())
                .build();
        return sizeDto;
    }

    public SizeEntity dto_to_entity(SizeDto sizeDto){
        SizeEntity sizeEntity=SizeEntity.builder()
                .id(sizeDto.getId())
                .name(sizeDto.getName())
                .code(sizeDto.getCode())
                .build();
        return sizeEntity;
    }
}
