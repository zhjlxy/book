package com.book.entity;

import com.book.common.Role;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2016/4/25.
 *  系统用户实体类
 */
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private Role role;

    @Column(name="uts")
    private Date uts;

    @Column(name="cts")
    private Date cts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getUts() {
        return uts;
    }

    public void setUts(Date uts) {
        this.uts = uts;
    }

    public Date getCts() {
        return cts;
    }

    public void setCts(Date cts) {
        this.cts = cts;
    }
}
