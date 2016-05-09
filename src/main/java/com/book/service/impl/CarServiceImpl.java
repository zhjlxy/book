package com.book.service.impl;

import com.book.common.Car;
import com.book.dao.BookDao;
import com.book.entity.Book;
import com.book.service.BookService;
import com.book.service.CarService;
import com.book.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixuy on 2016/4/28.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private Car car;

    @Autowired
    private HttpSession session;
    @Override
    public boolean addToCar(int bookId) {
        String userId = (String)session.getAttribute(UserServiceImpl.USERID);
        if(StringUtils.isNotBlank(userId)){
            Book book  =   bookDao.get(bookId);
            if(book != null){
                car.addToCar(userId,book);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Book> getCar() {
        String userId = (String)session.getAttribute(UserServiceImpl.USERID);
        if(StringUtils.isNotBlank(userId)){
          return  car.getCar(userId);
        }

        return new ArrayList<Book>();
    }

    @Override
    public void delCar(int bookId) {
        List<Book>  car = getCar();
        if(car == null){
            throw new RuntimeException("用户购物车为空！");
        }
        for(Book book : car){
            if(book.getId() == bookId){
                car.remove(book);
                break;
            }
        }
    }
}
