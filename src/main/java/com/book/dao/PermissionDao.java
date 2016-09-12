package com.book.dao;

import com.book.entity.Permission;

import java.util.List;

/**
 * Created by lixuy on 2016/9/10.
 */
public interface PermissionDao  {
    List<Permission> list (List<Integer> ids);

}
