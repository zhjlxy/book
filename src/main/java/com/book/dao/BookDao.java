package com.book.dao;

import com.book.entity.Book;

import java.util.List;

/**
 * Created by lixuy on 2016/4/16.
 */
public interface BookDao extends  GenericDao<Book, Integer> {
   // 分页查询
    List<Book> list(int firstNum, int pageSize);
}
