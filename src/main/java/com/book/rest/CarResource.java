package com.book.rest;

import com.alibaba.fastjson.JSONArray;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.common.Car;
import com.book.entity.Book;
import com.book.service.CarService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lixuy on 2016/4/28.
 */
@Controller
@RequestMapping(value="/car")
public class CarResource {
    private static final Logger logger = Logger.getLogger(CarResource.class);

    @Autowired
    private CarService carService;
    @RequestMapping(value="/add", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message add(@RequestParam("book_id") int bookId){
        Message msg = new Message();
        try{
            msg.setStatus(Status.ERROR);
            boolean result = carService.addToCar(bookId);
            if(result){
                msg.setStatus(Status.SUCCESS);
            }
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return msg;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message list(){
        Message msg  = new Message();
        try{
            List<Book> car = carService.getCar();
            msg.setData(JSONArray.toJSONString(car));
            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return msg;
    }

    @RequestMapping(value = "/del",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Message del(@RequestParam("bookId") int bookId){
        Message msg = new Message();
        try{
            msg.setStatus(Status.SUCCESS);
            carService.delCar(bookId);
        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }
}
