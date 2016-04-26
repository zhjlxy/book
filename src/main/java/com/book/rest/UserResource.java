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
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

    /**
     * 登录
     * @param userVo 登录的用户名密码
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message login(@RequestBody UserVo userVo){
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

    /**
     * {"valid":true}
     * @param username
     * @return
     */
    @RequestMapping(value = "/valid",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JSONObject validUserName( String username){
        JSONObject json = null;

        try{
            boolean result = userService.validUserName(username);
            json = new JSONObject();
            json.put("valid", result);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            json = new JSONObject();
            json.put("valid",false);
        }

        return json;
    }

    /**
     *  用户注册
     * @param userVo 用户注册信息
     * @return 用户id
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Message register(@RequestBody UserVo userVo){
        Message msg  = new Message();
        try{
            // 校验数据
            if(userVo == null || StringUtils.isBlank(userVo.getUser_name())
                    ||StringUtils.isBlank(userVo.getPassword()) || userVo.getRole()==null){
                msg.setStatus(Status.ERROR);
                msg.setStatusMsg("数据错误");
                return msg;
            }

            String userId = userService.register(userVo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            msg.setStatus(Status.ERROR);
            msg.setStatusMsg(e.getMessage());
        }
        return msg;
    }

}
