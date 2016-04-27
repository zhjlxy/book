package com.book.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.book.common.Role;
import com.book.dao.UserDao;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by admin on 2016/4/25.
 */
@Service
public class UserServiceImpl implements UserService {
    /**保存到session 中userId 的key*/
    private static final String USERID = "userId";

    @Autowired
    private UserDao userDao;

    @Autowired
    private HttpSession session;

    @Override
    public Role login(UserVo userVo) {

        User user = userDao.getByUserNameAndPassword(userVo);
        if(user!= null && StringUtils.isNotBlank(user.getId())){

            // 是否是对应的角色
            if(user.getRole().equals(userVo.getRole())){
                session.setAttribute(USERID,user.getId());
                return user.getRole();
            }
        }
        return null;
    }

    @Override
    public String register(UserVo userVo) {
        User user = VoToEntity(userVo);

        User role = userDao.get("858c6c932fbe450f807b62325f800847");
        JSONObject.toJSONString(role);
        return userDao.save(user);
    }

    /*
     * Vo 对象转实体对象
     */
    private User VoToEntity(UserVo userVo) {
        User user = new User();

        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUserName(userVo.getUser_name());
        user.setPassword(userVo.getPassword());
        user.setRole(userVo.getRole());

        return user;
    }


    @Override
    public boolean validUserName(String userName) {
        User user = userDao.getByUserName(userName);

        // 不存在验证通过
        if(user == null){
            return true;
        }
        return false;
    }

    @Override
    public String getUserName() {
        String result = "游客";
        String userId = (String) session.getAttribute(USERID);
        if(StringUtils.isNotBlank(userId)){
            User user = userDao.get(userId);
            if(user == null){
                throw new RuntimeException("session userId error");
            }
            result =  user.getUserName();
        }

        return result;
    }
}
