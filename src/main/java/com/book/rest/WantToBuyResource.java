package com.book.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.entity.WantToBuy;
import com.book.service.WantToBuyService;
import com.book.vo.PageResultVo;
import org.apache.log4j.Logger;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lixuy on 2016/5/28.
 */
@Controller
@RequestMapping(value = "want_to_buy")
public class WantToBuyResource {

    @Autowired
    private WantToBuyService wantToBuyService;

    /**日志工具*/
    private static final Logger logger = Logger.getLogger(UserResource.class);

    @RequestMapping(value = "{id}")
    public @ResponseBody Message getById(@PathVariable("id") String id){
        Message msg = new Message();
        try{
            WantToBuy wantToBuy = wantToBuyService.getBuyId(id);
            msg.setData(JSONObject.toJSONString(wantToBuy));
            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            logger.error(e, e);
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
        }

        return msg;
    }

    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Message add(@RequestBody WantToBuy wantToBuy){
        Message msg = new Message();
        try{

            String id =  wantToBuyService.add(wantToBuy);
            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            logger.error(e, e);
            msg.setStatusMsg(e.getMessage());
            msg.setStatus(Status.ERROR);
        }

        return msg;
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PageResultVo list(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
        PageResultVo resultVo =  new PageResultVo(true);
        try{
            List<WantToBuy> list = wantToBuyService.queryByPage(pageSize, pageNum);
            int count = wantToBuyService.queryPageTotal();
            resultVo.setData(JSONArray.toJSONString(list));
            resultVo.setCount(count%pageSize==0?count/pageSize:count/pageSize+1);
            resultVo.setPageNum(pageNum);
            resultVo.setFlag(true);

        }catch (Exception e){
            logger.error(e, e);
            resultVo.setFlag(false);
            resultVo.setMsg(e.getMessage());
        }

        return resultVo;
    }
}
