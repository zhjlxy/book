package com.book.dao;

import com.book.common.Role;
import com.book.entity.RolePermissionRel;

import java.util.List;

/**
 * Created by lixuy on 2016/9/10.
 */
public interface RolePermissionRelDao {
    List<RolePermissionRel> getByRole(Role role);
}
