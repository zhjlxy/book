package com.book.rest;

import com.alibaba.fastjson.JSONArray;
import com.book.entity.Book;
import com.book.service.BookService;
import com.book.vo.PageResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
@Controller
@RequestMapping("/book")
public class BookResource {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    PageResultVo list(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
        PageResultVo vo = new PageResultVo(true);

        List<Book> list =  bookService.list(pageSize, pageNum);
        int total = bookService.list().size();
        int count  = total%pageSize==0 ? total/pageSize : total/pageSize+1;
        vo.setCount(count);
        vo.setPageNum(pageNum);
        vo.setData(JSONArray.toJSONString(list));
        vo.setFlag(true);

        return  vo;
    }


    @RequestMapping(value = "{id}" ,method = RequestMethod.GET, produces="application/json")
    public @ResponseBody  Book getById(@PathVariable int id){
        Book book = bookService.getById(id);
        return book;

    }
}
