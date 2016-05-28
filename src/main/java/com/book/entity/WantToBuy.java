package com.book.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lixuy on 2016/5/28.
 */
@Table(name="want_to_buy")
@Entity
public class WantToBuy {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="user_id")
    private String  userId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="remark")
    private String remark;


    @Column(name="uts")
    private Date uts;

    @Column(name="cts")
    private Date cts;

    @Transient
    private String userName;

    @Transient
    private String ctsStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCtsStr() {
        return ctsStr;
    }

    public void setCtsStr(String ctsStr) {
        this.ctsStr = ctsStr;
    }
}
