package com.book.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lixuy on 2016/4/16.
 */
public class BaseDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

}
