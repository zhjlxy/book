package com.book.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
public interface GenericDao  <T,Id  extends Serializable>{

    T get(Id id);

    List<T> list();

    Id save(T entity);

    void saveOrUpdate(T entity);

    void delete(Id id);

}
