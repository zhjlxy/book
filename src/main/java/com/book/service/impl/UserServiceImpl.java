package com.book.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.book.common.Role;
import com.book.dao.UserDao;
import com.book.entity.User;
import com.book.service.UserService;
import com.book.vo.UserListVo;
import com.book.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by admin on 2016/4/25.
 */
@Service
public class UserServiceImpl implements UserService {
    /**保存到session 中userId 的key*/
    public static final String USERID = "userId";

    private static final String DEFAULT_PASSWORD = "123456";

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

    @Override
    public List<UserListVo> list(int pageSize, int pageNum, String userName) {
        int firstNum = pageSize*(pageNum-1);
        Map<String, Object> criteria = getCriteria(userName);
        List<User> users =  userDao.queryWithPage(firstNum, pageSize, criteria);
        return toUserListVoList(users);
    }

    /**
     * 对象转换
     * @param users
     * @return
     */
    private List<UserListVo> toUserListVoList(List<User> users) {
        List<UserListVo> result = new ArrayList<UserListVo>();
        if(users != null && !users.isEmpty()){
            for(User user : users){
                UserListVo userListVo = toUserListVo(user);
                result.add(userListVo);
            }
        }

        return result;
    }

    private UserListVo toUserListVo(User user) {
        if(user == null){
            return null;
        }
        UserListVo uv = new UserListVo();
        uv.setId(user.getId());
        uv.setRoleDesc(getRoleDesc(user.getRole()));
        uv.setUserName(user.getUserName());

        return uv;
    }

    private String getRoleDesc(Role role) {
        if(Role.ADMIN.equals(role)){
            return "管理员";
        }
        if(Role.BUYERS.equals(role)){
            return "买家";
        }
        if(Role.SELLER.equals(role)){
            return "卖家";
        }
        throw new RuntimeException("role error");

    }

    private Map<String,Object> getCriteria(String userName) {
        Map<String, Object> criteria = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(userName)){
            criteria.put("userName", userName);
        }
        return criteria;
    }

    @Override
    public int list(String userName) {
        Map<String, Object> criteria = getCriteria(userName);
        return userDao.queryTotal(criteria);
    }

    @Override
    public void resetPassword(String userId) {
        User user = userDao.get(userId);
        if(user == null){
            throw new RuntimeException("userId is error");
        }

        user.setPassword(this.DEFAULT_PASSWORD);
        userDao.saveOrUpdate(user);

    }

    @Override
    public void delete(String userId) {
        User user = userDao.get(userId);
        if(user == null){
            throw new RuntimeException("userId is error");
        }
        userDao.delete(userId);
    }

    @Override
    public void changePassword(String password) {
        String userId = (String) session.getAttribute(USERID);
        if (StringUtils.isNotBlank(userId)) {
            User user = userDao.get(userId);
            if (user == null) {
                throw new RuntimeException("session userId error");
            }
            if(user.getUserName().equalsIgnoreCase(password)){
                throw new RuntimeException("password can not equals userName");
            }
            user.setPassword(password);
            userDao.saveOrUpdate(user);
        }else{
            throw new RuntimeException("session userId not exit");
        }

    }
}
