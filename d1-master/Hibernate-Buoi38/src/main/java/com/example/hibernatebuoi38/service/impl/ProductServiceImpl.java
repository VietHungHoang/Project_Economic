package com.example.hibernatebuoi38.service.impl;

import com.example.hibernatebuoi38.dao.ICategoryDAO;
import com.example.hibernatebuoi38.dao.IColorDAO;
import com.example.hibernatebuoi38.dao.IProductDao;
import com.example.hibernatebuoi38.dao.ISizeDao;
import com.example.hibernatebuoi38.dto.ProductDto;
import com.example.hibernatebuoi38.entity.CategoryEntity;
import com.example.hibernatebuoi38.entity.ColorEntity;
import com.example.hibernatebuoi38.entity.ProductEntity;
import com.example.hibernatebuoi38.entity.SizeEntity;
import com.example.hibernatebuoi38.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    private final ModelMapper modelMapper;
    private final ICategoryDAO iCategoryDAO;
    private final ISizeDao iSizeDao;

    private final IProductDao iProductDao;
    private final IColorDAO iColorDAO;

    public ProductServiceImpl(ModelMapper modelMapper, ICategoryDAO iCategoryDAO, ISizeDao iSizeDao, IProductDao iProductDao, IColorDAO iColorDAO) {
        this.modelMapper = modelMapper;
        this.iCategoryDAO = iCategoryDAO;
        this.iSizeDao = iSizeDao;
        this.iProductDao = iProductDao;
        this.iColorDAO = iColorDAO;
    }

    @Override
    public void insert(ProductDto productDto) {
        ProductEntity productEntity=this.dto_to_entity(productDto);
        this.iProductDao.save(productEntity);
    }

    @Override
    public List<ProductDto> getAll() {
        return this.iProductDao.findAll().stream().map((entity)->{
            return this.entity_to_dto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        return null;
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        try{
            this.iProductDao.delete(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ProductDto> findByCategory(Long categoryId) {
        CategoryEntity categoryEntity=this.iCategoryDAO.findById(categoryId);
        return  this.iProductDao.findProductByCategory(categoryEntity).stream().map((entity)->{
            return this.entity_to_dto(entity);
        }).collect(Collectors.toList());
//        return categoryEntity.getProductEntities().stream().map((entity)->{
//            return this.entity_to_dto(entity);
//        }).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findBySize(Long sizeId) {
        SizeEntity sizeEntity=this.iSizeDao.findById(sizeId);
        return sizeEntity.getProductEntities().stream().map((entity)->{
            return this.entity_to_dto(entity);
        }).collect(Collectors.toList());
    }

    public ProductEntity dto_to_entity(ProductDto productDto){
        ProductEntity productEntity=ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .code(productDto.getCode())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .status(productDto.getStatus())
                .createDate(productDto.getCreateDate())
                .createBy(productDto.getCreateBy())
                .modifyDate(productDto.getModifyDate())
                .modifyBy(productDto.getModifyBy())
                .build();
        CategoryEntity categoryEntity=this.iCategoryDAO.findById(productDto.getCategoryId());
        List<SizeEntity> sizeEntities=this.iSizeDao.findByManyId(productDto.getSizeId());
        List<ColorEntity> colorEntities=this.iColorDAO.findByManyId(productDto.getColorId());
        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setSizeEntities(new HashSet<>(sizeEntities));
        productEntity.setColorEntities(new HashSet<>(colorEntities));
        return productEntity;
    }

    public ProductDto entity_to_dto(ProductEntity productEntity){
        ProductDto productDto=ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .code(productEntity.getCode())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .description(productEntity.getDescription())
                .status(productEntity.getStatus())
                .createDate(productEntity.getCreateDate())
                .createBy(productEntity.getCreateBy())
                .modifyDate(productEntity.getModifyDate())
                .modifyBy(productEntity.getModifyBy())
                .categoryId(productEntity.getCategoryEntity().getId())
                .categoryName(productEntity.getCategoryEntity().getName())
                .sizeId(
                        productEntity.getSizeEntities().stream().map((size)->{
                            return size.getId();
                        }).collect(Collectors.toList())
                )
                .sizeName(
                        productEntity.getSizeEntities().stream().map((size)->{
                            return size.getName();
                        }).collect(Collectors.toList())
                ).colorCode(
                        productEntity.getColorEntities().stream().map(color->{
                            return color.getCode();
                        }).collect(Collectors.toList())
                )
                .build();
        return productDto;
    }
}
