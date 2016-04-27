package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.service.BookService;
import com.book.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    private BookDao bookDao;

    @Autowired
    private HttpSession session;


    @Override
    public List<Book> list(String type) {
        return bookDao.list(type);
    }

    @Override
    public List<Book> list(int pageSize, int pageNum, String type) {
        int firstNum = pageSize*(pageNum-1);
        return bookDao.list(firstNum, pageSize, type);
    }

    @Override
    public BookVo getById(int id) {
        Book book =  bookDao.get(id);

        return EntityToVo(book);
    }

    /**
     * 添加书的service 方法
     *      返回添加成功后的id；
     * @param bookVo 要添加书的对象
     * @return 添加的书的Id
     */
    @Override
    public int saveOrUpdate(BookVo bookVo) {
        Book book = voToEntity(bookVo);

        if(bookVo.getId()==null){
            // add
            return bookDao.save(book);
        }else{
            //update
            bookDao.saveOrUpdate(book);
            return bookVo.getId();
        }
    }

    private Book voToEntity(BookVo bookVo) {
        Book book = new Book();
        book.setAuthor(bookVo.getAuthor());
        book.setName(bookVo.getName());
        book.setNewStatus(bookVo.getNew_status());
        book.setDesc(bookVo.getDesc());
        book.setPicture("/"+bookVo.getPicture());
        book.setPrice(bookVo.getPrice());

        // update
        if(bookVo.getId()!= null){
            book.setId(bookVo.getId());
        }

        // 用户
        String userId = (String) session.getAttribute("userId");
        userId = "123";
        book.setSellUserId(userId);
        book.setSellStatus(Book.SELL_IN);

        return book;
    }

    public BookVo EntityToVo(Book book){
        BookVo vo = new BookVo();
        if(book != null){
            vo.setId(book.getId());
            vo.setPicture(book.getPicture());
            vo.setDesc(book.getDesc());
            vo.setAuthor(book.getAuthor());
            vo.setName(book.getName());
            vo.setNew_status(book.getNewStatus());
            vo.setPrice(book.getPrice());
        }
        return vo;
    }
}
