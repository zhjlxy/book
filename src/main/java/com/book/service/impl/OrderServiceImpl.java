package com.book.service.impl;

import com.book.common.Car;
import com.book.common.Dateutil;
import com.book.dao.BookDao;
import com.book.dao.OrderDao;
import com.book.dao.OrderInfoDao;
import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderInfo;
import com.book.service.OrderService;
import com.book.vo.OrderListVo;
import com.book.vo.OrderVo;
import org.apache.commons.lang.StringUtils;
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
    private BookDao bookDao;

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

        String userId = (String) session.getAttribute(UserServiceImpl.USERID);
        List<Book> myCar =  car.getCar(userId);
        if(myCar == null || myCar.isEmpty()){
            throw new RuntimeException("购物车信息为空！！");
        }
        // 修改购物车中的书的状态为已销售
        updateBookStatus(myCar);
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
        //

        // 删除购物车中信息
        car.delCar(userId);


        return true;
    }

    /**
     * 修改购物车中书的状态
     * @param myCar
     */
    private void updateBookStatus(List<Book> myCar) {
        if(myCar!=null && !myCar.isEmpty()){
            for(Book book : myCar){
                Book nowStatus = bookDao.get(book.getId());
                if(book==null){
                    throw new RuntimeException("system error");
                }
               if(!Book.SELL_IN.equals(nowStatus.getSellStatus())){
                   throw new RuntimeException("have sell"+nowStatus.getName());
               }
                nowStatus.setSellStatus(Book.SELL_OVER);
                bookDao.saveOrUpdate(nowStatus);
            }
        }
    }

    /**
     * 获取订单详情
     * @return
     */
    @Override
    public List<OrderListVo> list() {
        //TODO 用户订单列表
        String userId = (String) session.getAttribute(UserServiceImpl.USERID);
        List<Order> orders = orderDao.getByUserId(userId);

        return getOrderListVo(orders);
    }

    /**
     *  更新订单状态
     * @param vo
     */
    @Override
    public void updateStatus(OrderVo vo) {
        if(vo == null || StringUtils.isBlank(vo.getId())){
            new RuntimeException("paramter error!");
        }
        Order order = orderDao.get(vo.getId());
        if(order != null){
            if(!order.getStatus().equalsIgnoreCase(vo.getStatus())){
                order.setStatus(vo.getStatus().toUpperCase());
                orderDao.saveOrUpdate(order);
            }
        }

    }

    private List<OrderListVo> getOrderListVo(List<Order> orders) {
        List<OrderListVo> olv = new ArrayList<>();
        if(orders == null || orders.isEmpty()){
            return olv;
        }
        for(Order order : orders){
            List<OrderInfo> orderInfos =  orderInfoDao.getByOrderId(order.getId());
            List<Book> bookList = getBookListByOrderInfos(orderInfos);
            OrderListVo orderListVo =  buildOrderListBo(bookList, order);
            olv.add(orderListVo);
        }

        return olv;
    }


    private OrderListVo buildOrderListBo(List<Book> bookList, Order order) {
        OrderListVo olv = new OrderListVo();
        olv.setId(order.getId());
        olv.setStatus(order.getStatus());
        olv.setStatusDesc(getOrderStatusDescByStatus(order.getStatus()));
        olv.setTotal(order.getTotal());
        olv.setBookNum(order.getBookNum());
        olv.setAddress(order.getAddress());
        olv.setBuyUser(order.getBuyUser());
        olv.setName(order.getName());
        olv.setRemarks(order.getRemarks());
        olv.setTel(order.getTel());
        olv.setCts(Dateutil.DateToStr(order.getCts()));
        olv.setBookList(bookList);

        return olv;
    }

    private String getOrderStatusDescByStatus(String status){
        if(Order.NON_PAYMENT.equalsIgnoreCase(status)){
            return Order.NON_PAYMENT_DESC;
        }else if(Order.FINISH.equalsIgnoreCase(status)){
            return Order.FINISH_DESC;
        }else if(Order.DEL.equalsIgnoreCase(status)){
            return Order.DEL_DESC;
        }

        return StringUtils.EMPTY;

    }

    /**
     * 根据orderInfos查询book信息
     * @param orderInfos
     * @return
     */
    private List<Book> getBookListByOrderInfos(List<OrderInfo> orderInfos) {
        List<Book> books = new ArrayList<>();
        if(orderInfos == null || orderInfos.isEmpty()){
            return books;
        }
        for(OrderInfo oi : orderInfos){
            Book book = bookDao.get(oi.getBookId());
            if(book !=null){
                books.add(book);
            }
        }
        return books;
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
        order.setName(vo.getName());
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
