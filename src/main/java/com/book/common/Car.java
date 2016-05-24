package com.book.common;

import com.book.entity.Book;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lixuy on 2016/4/28.
 * 购物车类
 */
@Component
public class Car {
    /**用于保存系统中用户的购物车*/
    private static final HashMap<String, List<Book>> catMap = new HashMap<String, List<Book>>();

    /**
     *  用户将课本添加到自己的购物侧重
     * @param userId 用户Id
     * @param book 要添加的
     * @return
     */
    public boolean addToCar(String userId, Book book){
        if(StringUtils.isBlank(userId) || book == null){
            return false;
        }
        List<Book> userCar = catMap.get(userId);
        if(userCar == null){
            userCar = new ArrayList<Book>();
            catMap.put(userId,userCar);
        }

        // 添加到购物侧中
        userCar.add(book);

        return true;
    }

    /**
     *  用户取自己的购物车
     * @param userId 用户Id
     * @return 用户的购物车，如果没有
     *              返回null。
     */
    public List<Book> getCar(String userId){
        return catMap.get(userId);
    }

    /**
     * 删除用户的购物车
     * @param userId
     */
    public void delCar(String userId){
        if(StringUtils.isNotBlank(userId)){
            catMap.remove(userId);
        }
    }
}
