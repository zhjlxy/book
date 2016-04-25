package com.book.vo;

import com.book.common.Role;

/**
 * Created by admin on 2016/4/25.
 * 用户Vo类
 */

public class UserVo {
    private String user_name;

    private String password;

    private Role role;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
