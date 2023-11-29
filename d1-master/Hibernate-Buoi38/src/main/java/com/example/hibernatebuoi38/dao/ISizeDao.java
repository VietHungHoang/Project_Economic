package com.example.hibernatebuoi38.dao;

import com.example.hibernatebuoi38.entity.SizeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ISizeDao extends IGenericDAO<SizeEntity>  {
    List<SizeEntity>findByManyId(List<Long> ids);
}
