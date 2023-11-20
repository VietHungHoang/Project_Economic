package com.example.hibernatebuoi38.dao;

import com.example.hibernatebuoi38.entity.ColorEntity;

import java.util.List;

public interface IColorDAO extends IGenericDAO<ColorEntity>{
    List<ColorEntity> findByManyId(List<Long> ids);
}
