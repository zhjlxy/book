package com.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by lixuy on 2016/5/10.
 */
@Table(name="order_info")
@Entity
public class OrderInfo {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="book_id")
    private int bookId;

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
