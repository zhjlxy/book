package com.book.service.impl;

import com.book.common.Car;
import com.book.dao.OrderDao;
import com.book.dao.OrderInfoDao;
import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderInfo;
import com.book.service.OrderService;
import com.book.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lixuy on 2016/5/10.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private Car car;


    @Override
    /**
     * 将购物车中的信息生成订单
     */
    @Transactional(rollbackOn=Exception.class)
    public boolean addOrder(OrderVo vo) {

        String userId = (String) session.getAttribute("userId");
        List<Book> myCar =  car.getCar(userId);
        if(myCar == null || myCar.isEmpty()){
            throw new RuntimeException("购物车信息为空！！");
        }
        // 订单
        Order order = getOrder(myCar,vo);
        // 订单详情
        List<OrderInfo> orderInfos = getOrderInfo(myCar,order);

        // 保存订单
        orderDao.save(order);
        // 保存订单详情
        for(OrderInfo oi : orderInfos){
            orderInfoDao.save(oi);
        }
        return true;
    }

    /**
     * 根据购物车构建订单详情
     * @param myCar 购物车
     * @return 订单详情集合
     */
    private List<OrderInfo> getOrderInfo(List<Book> myCar,Order order) {
        List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
        for(Book book : myCar){
            OrderInfo oi = new OrderInfo();
            oi.setId(UUID.randomUUID().toString().replaceAll("-",""));
            oi.setBookId(book.getId());
            oi.setOrderId(order.getId());
            orderInfos.add(oi);
        }

        return orderInfos;
    }

    /**
     * 根据购物车信息，生成订单信息
     * @param myCar 购物车信息
     * @param vo 订单信息
     * @return
     */
    private Order getOrder(List<Book> myCar, OrderVo vo) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replaceAll("-",""));
        order.setAddress(vo.getAddress());
        order.setBookNum(myCar.size());
        order.setTotal(getTotalByCar(myCar));
        order.setStatus(Order.NON_PAYMENT);
        order.setAddress(vo.getAddress());
        order.setTel(vo.getTel());
        order.setRemarks(vo.getRemarks());
        order.setBuyUser((String) session.getAttribute(UserServiceImpl.USERID));
        return order;
    }

    /**
     * 统计购物车的总价
     * @param myCar 购物车
     * @return 总价
     */
    private double getTotalByCar(List<Book> myCar) {
        double total = 0d;
        for(Book book : myCar){
            total = book.getPrice()+total;
        }
        return total;
    }
}
