package com.book.vo;

/**
 * Created by lixuy on 2016/4/24.
 * 添加书的VO类
 */
public class BookVo {

    private Integer id;

    private String name;

    private String author;

    private double price;

    private String new_status;

    private String desc;

    private String picture;

    private int type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNew_status() {
        return new_status;
    }

    public void setNew_status(String new_status) {
        this.new_status = new_status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
