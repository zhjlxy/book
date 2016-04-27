package com.book.rest;

import com.alibaba.fastjson.JSONObject;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.entity.BookType;
import com.book.service.BookTypeService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lixuy on 2016/4/27.
 */
@Controller
@RequestMapping(value = "bookType")
public class BookTypeResource {

    private static final Logger logger = Logger.getLogger(BookTypeResource.class);

    @Autowired
    private BookTypeService bookTypeService;
    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message list(){
        Message msg = new Message();
        try {
            String json = "[]";
            List<BookType> list = bookTypeService.list();
            if(list != null){
                json = JSONObject.toJSONString(list);
            }
            msg.setData(json);
            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
        }

        return msg;
    }
}
