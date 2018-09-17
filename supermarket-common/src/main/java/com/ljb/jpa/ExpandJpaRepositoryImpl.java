package com.ljb.jpa;

import com.ljb.exception.DataException;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class ExpandJpaRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,Serializable> implements ExpandJpaRepository<T,ID> {
    private final EntityManager em;
    private final JpaEntityInformation<T, ?> entityInformation;

    public ExpandJpaRepositoryImpl(JpaEntityInformation<T, ?> em, EntityManager entityManager) {
        super(em, entityManager);
        Assert.notNull(em, "JpaEntityInformation must not be null!");
        Assert.notNull(entityManager, "EntityManager must not be null!");
        this.entityInformation = em;
        this.em = entityManager;
    }
    
    public ExpandJpaRepositoryImpl(Class<T> domainClass,EntityManager em){
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em),em);
    }

    public T findOne(Serializable id){
        return findById(id).orElse(null);
    }
}
