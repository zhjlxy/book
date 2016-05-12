package com.book.dao.impl;

import com.book.dao.OrderInfoDao;
import com.book.entity.OrderInfo;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lixuy on 2016/5/10.
 */
@Component
public class OrderInfoDaoImpl extends BaseDaoImpl implements OrderInfoDao {
    @Override
    public OrderInfo get(String s) {
        return (OrderInfo) getCurrentSession().get(OrderInfo.class, s);
    }

    @Override
    public List<OrderInfo> list() {
        return getCurrentSession().createQuery("from OrderInfo").list();
    }

    @Override
    public String save(OrderInfo entity) {
        return (String) getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(OrderInfo entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(String s) {
        OrderInfo orderInfo = get(s);
        if(orderInfo != null){
            getCurrentSession().delete(orderInfo);
        }
    }

    /**
     * 根据orderId 查询orderInfo
     * @param orderId 订单id
     * @return
     */
    @Override
    public List<OrderInfo> getByOrderId(String orderId) {
        Query query = getCurrentSession().createQuery("from OrderInfo where  orderId = :orderId");
        query.setString("orderId", orderId);
        return query.list();
    }
}
