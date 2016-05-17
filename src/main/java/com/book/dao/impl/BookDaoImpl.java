package com.book.dao.impl;

import com.book.dao.BookDao;
import com.book.entity.Book;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */

@Component
public class BookDaoImpl extends BaseDaoImpl implements BookDao {

    @Override
    public Book get(Integer integer) {
        return (Book) getCurrentSession().get(Book.class, integer);
    }

    @Override
    public List<Book> list() {
        List<Book> result = getCurrentSession().createQuery("from Book").list();

        return result;
    }

    /**
     *  按类型查询
     * @param type 类型
     * @return
     */
    public List<Book> list(String type) {

        String sql = "from Book";
        if(StringUtils.isNotBlank(type)){
            sql = sql + " where typeId=?";
        }

        Query query = getCurrentSession().createQuery(sql);
        if(StringUtils.isNotBlank(type)){
            query.setInteger(0,Integer.parseInt(type));
        }

        List<Book> result =  query.list();

        return result;

    }

    @Override
    public List<Book> list(int firstNum, int pageSize, String type) {
        String sql = "from Book";
        if(StringUtils.isNotBlank(type)){
            sql = sql + " where typeId=?";
        }

        Query query = getCurrentSession().createQuery(sql);
        if(StringUtils.isNotBlank(type)){
            query.setInteger(0,Integer.parseInt(type));
        }

        query.setFirstResult(firstNum);
        query.setMaxResults(pageSize);

        List<Book> result =  query.list();

        return result;
    }

    @Override
    public Integer save(Book entity) {
        return (Integer) getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(Book entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(Integer integer) {
        Book book  = get(integer);
        if(book != null){
            getCurrentSession().delete(book);
        }
    }

    @Override
    public String getBaseSql() {
        return "from Book";
    }
}
