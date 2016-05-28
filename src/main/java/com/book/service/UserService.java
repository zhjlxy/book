package com.book.service;

import com.book.common.Role;
import com.book.entity.User;
import com.book.vo.UserListVo;
import com.book.vo.UserVo;

import java.util.List;

/**
 * Created by admin on 2016/4/25.
 */
public interface UserService {
    /**
     *  用户登录接口，登录成功，返回用户的角色对象
     *  失败，返回null
     * @param userVo
     * @return
     */
    public Role login(UserVo userVo);

    /**
     *  用户注册
     * @param userVo 用户注册信息
     * @return 用户Id
     */
    public String register(UserVo userVo);

    /**
     * 校验用户名是否存在
     * @param userName
     * @return
     */
    public boolean validUserName(String userName);

    /**
     * 取登录的用户名
     * @return 登录的用户名，如果没有登录，返回游客
     */
    public User getUserName();

    /**
     * 根据userName模糊查询用户，如果userName为空，查询全部
     * @param pageSize 当前的页数
     * @param pageNum  每页的条数
     * @param userName 用户名
     * @return 符合条件的用户集合
     */
    public List<UserListVo> list(int pageSize, int pageNum, String userName);

    /**
     *  查询符合条件的用户集合
     * @param userName 用户名
     * @return 符合条件的所有用户的集合
     */
    public int list(String userName);

    public void resetPassword(String userId);

    public void delete(String userId);

    public void changePassword(String password);

    public  String getUserNameById(String userId);
}
