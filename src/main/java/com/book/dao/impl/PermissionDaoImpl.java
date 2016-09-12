package com.book.dao.impl;

import com.book.dao.PermissionDao;
import com.book.entity.Permission;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixuy on 2016/9/10.
 */
@Component
public class PermissionDaoImpl extends  BaseDaoImpl implements PermissionDao{
    @Override
    public List<Permission> list(List<Integer> ids) {
        if(ids!=null && !ids.isEmpty()){
            Query query = this.getCurrentSession().createQuery("from Permission where id in(:ids)");
            query.setParameterList("ids",ids);
            return query.list();
        }
        return new ArrayList<>();
    }

    @Override
    public String getBaseSql() {
        return "from Permission";
    }
}
