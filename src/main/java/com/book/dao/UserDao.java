package com.book.dao;

import com.book.entity.User;
import com.book.vo.UserVo;

/**
 * Created by admin on 2016/4/25.
 */
public interface UserDao extends  GenericDao<User,String> {
    public User getByUserNameAndPassword(UserVo userVo);
}
