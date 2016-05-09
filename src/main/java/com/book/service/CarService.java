package com.book.service;

import com.book.entity.Book;

import java.util.List;

/**
 * Created by lixuy on 2016/4/28.
 */
public interface CarService {
    public boolean addToCar(int  bookId);

    public List<Book> getCar();

    public void delCar(int bookId);
}
