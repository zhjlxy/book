package com.book.rest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.entity.Order;
import com.book.service.OrderService;
import com.book.vo.OrderListVo;
import com.book.vo.OrderVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public @ResponseBody Message addOrder(@RequestBody OrderVo vo){
        Message msg = new Message();
        try{
            boolean result = orderService.addOrder(vo);
            msg.setStatus(Status.SUCCESS);
            JSONObject json = new JSONObject();
            json.put("result","下单成功！");
            msg.setData(json.toString());
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message list(){
        Message msg = new Message();
        try{
            List<OrderListVo> orderListVos =  orderService.list();
            msg.setStatus(Status.SUCCESS);
            msg.setData(JSONObject.toJSONString(orderListVos, SerializerFeature.DisableCircularReferenceDetect));
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e, e);
        }
        return  msg;
    }

    @RequestMapping(value = "/update_status", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message updateStatus(@RequestBody OrderVo vo){
        Message msg = new Message();
        try{
            if(!checkStatus(vo.getStatus())){
                msg.setStatus(Status.ERROR);
                msg.setStatusMsg("status error!");
                return msg;
            }
            orderService.updateStatus(vo);

            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e, e);

        }

        return msg;
    }

    /**
     *
     */
    private boolean checkStatus(String status){
        if(Order.DEL.equalsIgnoreCase(status)){
            return true;
        }

        if(Order.FINISH.equalsIgnoreCase(status)){
            return true;
        }
        return  false;
    }
}
