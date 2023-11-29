package com.example.hibernatebuoi38.dao.impl;

import com.example.hibernatebuoi38.dao.ICategoryDAO;
import com.example.hibernatebuoi38.entity.CategoryEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryDAOImpl extends GenericDAOImpl<CategoryEntity> implements ICategoryDAO {

    private final SessionFactory sessionFactory;
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        super(CategoryEntity.class, sessionFactory);
        this.sessionFactory=sessionFactory;
    }
}
