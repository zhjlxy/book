package com.book.entity;

import com.book.common.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lixuy on 2016/9/10.
 */
@Table(name="role_permission_rel")
@Entity
public class RolePermissionRel {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="role_id")
    private Role roleId;

    @Column(name="permission_id")
    private int permissionId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
