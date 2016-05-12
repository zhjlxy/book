package com.book.service;

import com.book.vo.OrderListVo;
import com.book.vo.OrderVo;

import java.util.List;

/**
 * Created by lixuy on 2016/5/10.
 */
public interface OrderService {
    public boolean addOrder(OrderVo vo);

    public List<OrderListVo> list();
}
