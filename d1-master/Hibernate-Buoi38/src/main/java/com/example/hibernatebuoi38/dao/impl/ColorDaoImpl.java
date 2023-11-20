package com.example.hibernatebuoi38.dao.impl;

import com.example.hibernatebuoi38.dao.IColorDAO;
import com.example.hibernatebuoi38.entity.ColorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ColorDaoImpl extends GenericDAOImpl<ColorEntity> implements IColorDAO {
    private final SessionFactory sessionFactory;
    public ColorDaoImpl(SessionFactory sessionFactory) {
        super(ColorEntity.class, sessionFactory);
        this.sessionFactory=sessionFactory;
    }


    @Override
    public List<ColorEntity> findByManyId(List<Long> ids) {
        Session session=sessionFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<ColorEntity> criteriaQuery= criteriaBuilder.createQuery(ColorEntity.class);
        Root<ColorEntity> root= criteriaQuery.from(ColorEntity.class);
        criteriaQuery.select(root).where(root.get("id").in(ids));
        Query query= session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
