package com.example.hibernatebuoi38.service.impl;

import com.example.hibernatebuoi38.dao.ICategoryDAO;
import com.example.hibernatebuoi38.dto.CategoryDTO;
import com.example.hibernatebuoi38.entity.CategoryEntity;
import com.example.hibernatebuoi38.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryDAO iCategoryDAO;

    public CategoryServiceImpl(ICategoryDAO iCategoryDAO) {
        this.iCategoryDAO = iCategoryDAO;
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        this.iCategoryDAO.save(dto_to_entity(categoryDTO));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return this.iCategoryDAO.findAll().stream().map(entity->{
            return entity_to_dto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity=this.iCategoryDAO.update(dto_to_entity(categoryDTO));
        return this.entity_to_dto(categoryEntity);
    }

    @Override
    public CategoryDTO findById(Long id) {
        CategoryEntity categoryEntity=this.iCategoryDAO.findById(id);
        return this.entity_to_dto(categoryEntity);
    }

    @Override
    public void delete(Long id) {
        try {
            this.iCategoryDAO.delete(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private CategoryEntity dto_to_entity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setName(categoryDTO.getName());
        return categoryEntity;
    }
    private CategoryDTO entity_to_dto(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO= CategoryDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
        return categoryDTO;
    }
}
