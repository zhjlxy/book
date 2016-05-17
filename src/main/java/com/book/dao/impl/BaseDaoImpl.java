package com.book.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lixuy on 2016/4/16.
 */
public abstract class BaseDaoImpl <T,Id  extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 分页查询
     * @param firstNum 当前页
     * @param pageSize 一页条数
     * @param criteria 条件
     * @return
     */
    public List<T> queryWithPage(int firstNum, int pageSize,Map<String, Object> criteria ){
        Query query = getQuery(criteria);

        query.setFirstResult(firstNum);
        query.setMaxResults(pageSize);
        return query.list();
    }


    /**
     * 总条数
     * @param criteria 条件
     * @return
     */
    public int queryTotal(Map<String, Object> criteria ){
        Query query = getQuery(criteria);

        return query.list().size();
    }

    private Query getQuery(Map<String, Object> criteria) {
        StringBuilder sb = new StringBuilder();
        sb.append(getBaseSql());
        if(criteria != null && !criteria.isEmpty()){
            sb.append(" where");
            Set<String> keys = criteria.keySet();
            Iterator<String> it = keys.iterator();
            while(it.hasNext()){
                sb.append(" ");
                sb.append(it.next());
                sb.append("=?");
                sb.append(" and");
            }
            sb.delete(sb.length()-4, sb.length());
        }

        Query query = getCurrentSession().createQuery(sb.toString());
        if(criteria != null && !criteria.isEmpty()){
            Set<String> keys = criteria.keySet();
            Iterator<String> it = keys.iterator();
            int count = 0;
            while(it.hasNext()){
                query.setParameter(count, criteria.get(it.next()));
                count++;
            }
        }
        return query;
    }

    /**
     * 基本的sql语句
     * @return
     */
    public abstract String getBaseSql();

}
