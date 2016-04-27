package com.book.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lixuy on 2016/4/27.
 *  书类型信息
 */
@Table(name="book_type")
@Entity
public class BookType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="desc")
    private String desc;

    @Column(name="cts")
    private Date cts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCts() {
        return cts;
    }

    public void setCts(Date cts) {
        this.cts = cts;
    }
}
