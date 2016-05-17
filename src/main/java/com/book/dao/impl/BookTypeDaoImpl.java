package com.book.dao.impl;

import com.book.dao.BookDao;
import com.book.dao.BookTypeDao;
import com.book.entity.Book;
import com.book.entity.BookType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lixuy on 2016/4/27.
 */
@Component
public class BookTypeDaoImpl extends BaseDaoImpl implements BookTypeDao {

    @Override
    public BookType get(Integer integer) {
        return (BookType) getCurrentSession().get(BookType.class,integer);
    }

    @Override
    public List<BookType> list() {
        return getCurrentSession().createQuery("from BookType").list();
    }

    @Override
    public java.lang.Integer save(BookType entity) {
        return (Integer)getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(BookType entity) {
            getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Integer integer) {
        BookType bookType = get(integer);
        if(bookType != null){
            getCurrentSession().delete(bookType);
        }
    }

    @Override
    public String getBaseSql() {
        return "from BookType";
    }
}
