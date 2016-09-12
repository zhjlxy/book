package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lixuy on 2016/9/10.
 */
@Table(name="permission")
@Entity
public class Permission {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="permission_name")
    private String permissionName;

    @Column(name="url")
    private String url;

    @Column(name="desc")
    private String desc;

    @Column(name="uts")
    private Date  uts;

    @Column(name="cts")
    private Date cts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
