package com.book.dao.impl;

import com.book.dao.UserDao;
import com.book.entity.User;
import com.book.vo.UserVo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Created by admin on 2016/4/25.
 * 用户的dao实现类
 */
@Component
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User get(String s) {
        return (User) getCurrentSession().get(User.class,s);
    }

    @Override
    public List<User> list() {
         List<User> result = getCurrentSession().createQuery("from Book").list();
        return result;
    }

    @Override
    public String save(User entity) {
        return (String)getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(User entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(String s) {
        User user  = get(s);
        if(user != null){
            getCurrentSession().delete(user);
        }
    }

    @Override
    public User getByUserNameAndPassword(UserVo userVo) {
        Objects.requireNonNull(userVo);
        List<User> users =  getCurrentSession().createQuery("from User where userName=? and password=?")
                .setString(0,userVo.getUser_name())
                .setString(1,userVo.getPassword()).list();

        // 没有匹配到用户
        if(users == null || users.isEmpty()){
            return null;
        }

        // 出现多条数据
        if(users.size()>1){
            throw new RuntimeException("查询结果大于1，请与管理员联系！");
        }
        return users.get(0);
    }

    @Override
    public User getByUserName(String userName) {
        List<User> users =  getCurrentSession().createQuery("from User user where user.userName=:username")
                .setString("username", userName).list();

        if(users == null || users.isEmpty()){
            return null;
        }

        if(users.size()>1){
            throw new RuntimeException("查询结果大于1，请与管理员联系！");
        }
        return users.get(0);
    }
}
