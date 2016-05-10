package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lixuy on 2016/5/10.
 */
@Table(name="order")
@Entity
public class Order {

    public static final String NON_PAYMENT="N";
    public static final String FINISH = "F";
    public static final String DEL ="D";
    @Id
    @Column(name="id")
    private String id;

    @Column(name="book_num")
    private int bookNum;

    @Column(name="total")
    private double total;

    @Column(name="buy_user")
    private int buyUser;

    @Column(name="address")
    private String address;

    @Column(name="status")
    private String status;

    @Column(name = "uts")
    private Date uts;

    @Column(name="cts")
    private Date cts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(int buyUser) {
        this.buyUser = buyUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
