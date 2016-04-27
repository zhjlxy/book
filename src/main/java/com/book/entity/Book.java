package com.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lixuy on 2016/4/16.
 */
@Table(name = "book")
@Entity
public class Book implements Serializable {

    /** 销售中 */
    public static final String SELL_IN = "in";

    /** 已卖出*/
    public static final String SELL_OVER = "over";

    /**已下架*/
    public static final String SELL_OFF = "off";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="book_name")
    private String name;

    @Column(name="author")
    private String author;

    @Column(name="book_desc")
    private String desc;

    @Column(name="price")
    private double price;

    @Column(name="picture")
    private String picture;

    @Column(name="sell_userId")
    private String  sellUserId;

    @Column(name="new_status")
    private String newStatus;

    @Column(name="type_id")
    private int typeId;;

    @Column(name="sell_status")
    private String sellStatus;

    @Column(name="cts")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cts;

    @Column(name = "uts")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(String sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Date getCts() {
        return cts;
    }

    public void setCts(Date cts) {
        this.cts = cts;
    }

    public Date getUts() {
        return uts;
    }

    public void setUts(Date uts) {
        this.uts = uts;
    }
}
