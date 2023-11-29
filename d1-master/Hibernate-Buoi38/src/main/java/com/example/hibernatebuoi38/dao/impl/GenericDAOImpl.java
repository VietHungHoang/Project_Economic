package com.example.hibernatebuoi38.dao.impl;

import com.example.hibernatebuoi38.dao.IGenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class GenericDAOImpl<T> implements IGenericDAO<T> {

    private final Class<T> tClass;
    private final SessionFactory sessionFactory;

    public GenericDAOImpl(Class<T> tClass, SessionFactory sessionFactory) {
        this.tClass = tClass;
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<T> findAll() {
        Session session=sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=criteriaBuilder.createQuery(tClass);
        Root<T> root=criteriaQuery.from(tClass);
        criteriaQuery.select(root).where();
        Query query=session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public T findById(Long id) {
        return this.sessionFactory.openSession().get(tClass,id);
    }

    @Override
    public void save(T t) {
        Session session=sessionFactory.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.merge(t);
            transaction.commit();
            System.out.println("create success");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error save!");
            System.out.println(e.getMessage());
            transaction.rollback();
        }finally {
            session.close();
        }
    }


    @Override
    public T update(T t) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        T tUpdate=null;
        try{
            tUpdate = (T) session.merge(t);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            transaction.rollback();
        }finally {
            session.close();
        }
        return tUpdate;
    }

    @Override
    public void delete(Long id) throws SQLException {
        Session session= sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        T t=session.get(tClass,id);
        if(t==null){
            throw new SQLException(String.format("Object has id = %d not exist",id));
        }
        session.delete(t);
        transaction.commit();
        session.close();
    }
    @Override
    public void insert(T t) {
        Session session=sessionFactory.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            session.save(t);
            transaction.commit();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            transaction.rollback();
        }finally {
            session.close();
        }
    }
}
