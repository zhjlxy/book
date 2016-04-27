package com.book.service;

import com.book.entity.BookType;

import java.util.List;

/**
 * Created by lixuy on 2016/4/27.
 */
public interface BookTypeService {

    /**
     * 返回所有的类型信息
     * @return 所有类型信息
     */
    public List<BookType> list();
}
