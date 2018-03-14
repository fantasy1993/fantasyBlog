package com.fantasy.user.controller;


import com.fantasy.user.model.User;
import com.fantasy.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 用户登录注册
 * @author fantasy
 * @since 2017-12-23
 */
@RestController
public class UserController {

    @Autowired
    IUserService userService;

    //验证登录
    @RequestMapping(value = "/validateLogin",method= RequestMethod.POST)
    public ResponseEntity<User> validateLogin(@RequestBody User user, HttpSession session) throws InvalidKeySpecException, NoSuchAlgorithmException {
        HttpStatus status;
        List<User> userList = new ArrayList<User>();
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            SecurityUtils.getSubject().login(token);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("username",user.getUsername());
            map.put("password",user.getPassword());
            userList = userService.selectByMap(map);
            session.setAttribute("userId",userList.get(0).getId());
            status = HttpStatus.OK;
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<User>(userList.get(0),status);

    }
}

