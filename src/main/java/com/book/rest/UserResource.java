package com.book.rest;

import com.alibaba.fastjson.JSONObject;
import com.book.bo.Message;
import com.book.bo.Status;
import com.book.common.Role;
import com.book.service.UserService;
import com.book.vo.UserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by admin on 2016/4/25.
 * 用户资源类
 */
@Controller
@RequestMapping("/user")
public class UserResource {

    /**日志工具*/
    private static final Logger logger = Logger.getLogger(UserResource.class);

    @Autowired
    private UserService userService;

    public @ResponseBody Message login(UserVo userVo){
        Message msg = new Message();
        try {
            if(userVo==null || StringUtils.isBlank(userVo.getUser_name()) || StringUtils.isBlank(userVo.getPassword())){
                msg.setStatus(Status.ERROR);
                msg.setStatusMsg("error");

                return msg;
            }

            Role role = userService.login(userVo);
            if(role == null){
                msg.setStatus(Status.ERROR);
                msg.setStatusMsg("用户名密码错误！");
            }else{
                msg.setData(new JSONObject().put("role", role).toString());
            }

        }catch (Exception e){
            logger.error(e.getMessage(), e);
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
        }
        return msg;
    }
}
