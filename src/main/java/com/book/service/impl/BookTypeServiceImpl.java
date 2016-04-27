package com.book.service.impl;

import com.book.dao.BookTypeDao;
import com.book.entity.BookType;
import com.book.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lixuy on 2016/4/27.
 */
@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeDao bookTypeDao;

    @Override
    public List<BookType> list() {
        return bookTypeDao.list();
    }
}
