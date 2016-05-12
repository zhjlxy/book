package com.book.dao;

import com.book.entity.Order;

import java.util.List;

/**
 * Created by lixuy on 2016/5/10.
 */
public interface OrderDao extends GenericDao<Order,String> {
    public  List<Order> getByUserId(String userId);
}
