package com.book.service;

import com.book.entity.Book;

import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
public interface BookService {
    public List<Book> list();

    // 分页
    public List<Book> list(int pageSize, int pageNum);

    public Book getById(int id);
}
