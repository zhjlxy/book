package com.book.service.impl;

import com.book.common.Role;
import com.book.dao.UserDao;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2016/4/25.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Role login(UserVo userVo) {

        User user = userDao.getByUserNameAndPassword(userVo);
        if(user!= null){
            return user.getRole();
        }
        return null;
    }
}
