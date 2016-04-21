package com.book.service.impl;

import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> list() {
        return bookDao.list();
    }

    @Override
    public List<Book> list(int pageSize, int pageNum) {
        int firstNum = pageSize*(pageNum-1);
        return bookDao.list(firstNum, pageSize);
    }

    @Override
    public Book getById(int id) {
        return bookDao.get(id);
    }
}
