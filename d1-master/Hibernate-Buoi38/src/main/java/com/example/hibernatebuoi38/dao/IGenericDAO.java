package com.example.hibernatebuoi38.dao;

import java.sql.SQLException;
import java.util.List;

public interface IGenericDAO <T>{
    List<T> findAll();
    T findById(Long id);

    void save(T t);

    T update(T t);

    void delete(Long id) throws SQLException;

    void insert(T t);
}
