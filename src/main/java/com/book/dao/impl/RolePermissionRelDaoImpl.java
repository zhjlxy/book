package com.book.dao.impl;

import com.book.common.Role;
import com.book.dao.RolePermissionRelDao;
import com.book.entity.RolePermissionRel;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lixuy on 2016/9/10.
 */
@Component
public class RolePermissionRelDaoImpl extends BaseDaoImpl implements RolePermissionRelDao{
    @Override
    public List<RolePermissionRel> getByRole(Role role) {
        Query query = this.getCurrentSession().createQuery("from RolePermissionRel where roleId=:role");
        query.setParameter("role", role);
        return query.list();
    }

    @Override
    public String getBaseSql() {
        return "from RolePermissionRel";
    }
}
