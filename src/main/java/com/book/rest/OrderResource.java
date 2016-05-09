package com.book.rest;

import com.book.bo.Message;
import com.book.bo.Status;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lixuy on 2016/5/2.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderResource {

    private Logger logger = Logger.getLogger(OrderResource.class);
    public @ResponseBody Message addOrder(){
        Message msg = new Message();
        try{

        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }
}
