package com.book.dao;

import com.book.entity.OrderInfo;

import java.util.List;

/**
 * Created by lixuy on 2016/5/10.
 */
public interface OrderInfoDao extends GenericDao<OrderInfo,String> {
    public List<OrderInfo> getByOrderId(String orderId);
}
