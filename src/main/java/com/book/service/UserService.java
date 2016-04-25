package com.book.service;

import com.book.common.Role;
import com.book.vo.UserVo;

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
}
