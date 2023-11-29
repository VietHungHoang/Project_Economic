package com.example.hibernatebuoi38.dao.impl;

import com.example.hibernatebuoi38.dao.ISizeDao;
import com.example.hibernatebuoi38.entity.SizeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SizeDaoImpl extends GenericDAOImpl<SizeEntity> implements ISizeDao {
    private final SessionFactory sessionFactory;
    public SizeDaoImpl( SessionFactory sessionFactory) {
        super(SizeEntity.class, sessionFactory);
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<SizeEntity> findByManyId(List<Long> ids) {
        Session session= sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<SizeEntity> criteriaQuery=criteriaBuilder.createQuery(SizeEntity.class);
        Root<SizeEntity>root= criteriaQuery.from(SizeEntity.class);
        criteriaQuery.select(root).where(
            root.get("id").in(ids)
        );
        Query query=session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
