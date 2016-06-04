    package com.book.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.entity.Book;
import com.book.service.BookService;
import com.book.utils.FileUtils;
import com.book.vo.BookListVo;
import com.book.vo.BookVo;
import com.book.vo.PageResultVo;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
@Controller
@RequestMapping("/book")
public class BookResource {

    private static final Logger logger = Logger.getLogger(BookResource.class);

    private static final String BOOK_IMG_PATH = "/img/";

    @Autowired
    private BookService bookService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "{id}" ,method = RequestMethod.GET, produces="application/json")
    public @ResponseBody  Message getById(@PathVariable int id){
        Message msg = new Message();
        try{
            BookVo book = bookService.getById(id);
            msg.setStatus(Status.SUCCESS);
            msg.setData(JSONObject.toJSONString(book));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            msg.setStatusMsg(e.getMessage());
        }
        return msg;

    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PageResultVo list(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum,
                    @RequestParam(value = "type", required = false) String type,
                    @RequestParam(value = "bookName", required = false) String bookName){
        PageResultVo vo = new PageResultVo(true);
        if(StringUtils.isNotBlank(bookName)){
            try {
                bookName = URLDecoder.decode(bookName,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error(e, e);
                bookName = StringUtils.EMPTY;
            }
        }

        List<Book> list =  bookService.list(pageSize, pageNum, type, bookName);
        int total = bookService.list(type, bookName).size();
        int count  = total%pageSize==0 ? total/pageSize : total/pageSize+1;
        vo.setCount(count);
        vo.setPageNum(pageNum);
        vo.setData(JSONArray.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
        vo.setFlag(true);

        return  vo;
    }

    @RequestMapping(value="/upload",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message upload(@RequestParam("img") MultipartFile file){
        System.out.println(file);
        Message msg = new Message();

        try{
            InputStream in = file.getInputStream();
            String path = session.getServletContext().getRealPath(BOOK_IMG_PATH);
            File resultFile = new File(path+File.separator+file.getOriginalFilename());

            FileUtils.upload(in, resultFile);

            msg.setStatus(Status.SUCCESS);
            msg.setStatusMsg("File upload success");
            JSONObject json = new JSONObject();
            json.put("picture",resultFile.getName() );
            msg.setData(json.toString());

        }catch (Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }

    @RequestMapping(value = "/save_or_update",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message  saveOrUpdate(@RequestBody BookVo bookVo){
        Message msg = new Message();
        try{

            int bookId = bookService.saveOrUpdate(bookVo);
            JSONObject json = new JSONObject();
            json.put("Id",bookId);
            msg.setData(json.toString());
            msg.setStatus(Status.SUCCESS);
        }catch(Exception e){
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }

        return msg;
    }

    @RequestMapping(value = "/sell_book",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PageResultVo querySellBook(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
        PageResultVo vo = new PageResultVo(true);

        List<BookListVo> list =  bookService.querySellBook(pageSize, pageNum);
        int total = bookService.querySellBookTotal();
        int count  = total%pageSize==0 ? total/pageSize : total/pageSize+1;
        vo.setCount(count);
        vo.setPageNum(pageNum);
        vo.setData(JSONArray.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
        vo.setFlag(true);

        return vo;
    }

    @RequestMapping(value = "/auth_book",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PageResultVo queryAuthBook(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum){
        PageResultVo vo = new PageResultVo(true);

        List<BookListVo> list =  bookService.queryAuthBook(pageSize, pageNum);
        int total = bookService.queryAuthBookTotal();
        int count  = total%pageSize==0 ? total/pageSize : total/pageSize+1;
        vo.setCount(count);
        vo.setPageNum(pageNum);
        vo.setData(JSONArray.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
        vo.setFlag(true);

        return vo;
    }
    @RequestMapping(value="/update_sell_status")
    public @ResponseBody Message updateSellStatus(@RequestParam("bookId") int bookId, @RequestParam("sellStatus") String sellStatus){
        Message msg = new Message();
        try{
            bookService.updateSellStatus(bookId, sellStatus);
            msg.setStatus(Status.SUCCESS);
        }catch (Exception e){
            logger.error(e, e);
            msg.setStatusMsg(e.getMessage());
            msg.setStatus(Status.ERROR);
        }
        return msg;
    }
}
