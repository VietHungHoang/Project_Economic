package com.example.hibernatebuoi38.dao.impl;

import com.example.hibernatebuoi38.dao.IProductDao;
import com.example.hibernatebuoi38.entity.CategoryEntity;
import com.example.hibernatebuoi38.entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ProductDaoImpl extends GenericDAOImpl<ProductEntity> implements IProductDao {
    private final SessionFactory sessionFactory;
    public ProductDaoImpl(SessionFactory sessionFactory) {
        super(ProductEntity.class, sessionFactory);
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<ProductEntity> findProductByCategory(CategoryEntity category) {
        Session session=sessionFactory.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery=criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity>root=criteriaQuery.from(ProductEntity.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("categoryEntity"),category));
        Query query=session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}

