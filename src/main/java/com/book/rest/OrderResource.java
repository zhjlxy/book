package com.book.rest;

import com.alibaba.fastjson.JSONObject;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.service.OrderService;
import com.book.vo.OrderVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixuy on 2016/5/2.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    private Logger logger = Logger.getLogger(OrderResource.class);
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message addOrder(OrderVo vo){
        Message msg = new Message();
        try{
            boolean result = orderService.addOrder(vo);
            msg.setData("下单成功！");
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }
}
