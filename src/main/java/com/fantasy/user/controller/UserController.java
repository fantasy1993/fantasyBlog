package com.fantasy.user.controller;


import com.fantasy.base.FantasyResult;
import com.fantasy.base.FantasyResultCode;
import com.fantasy.user.model.User;
import com.fantasy.user.po.UserModel;
import com.fantasy.user.service.IUserService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

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

    /**
     * 验证注册
     * @param regUser
     */
    @RequestMapping(value = "/validateReg",method= RequestMethod.POST)
    public FantasyResult validateRegister(@RequestBody UserModel regUser)  {
        if(!regUser.getPassword().equals(regUser.getRePassword())){
            return new FantasyResult(FantasyResultCode.WARN,regUser);
        }
        User user = new User();
        user.setUsername(regUser.getUsername());
        user.setPassword(regUser.getPassword());
        user.setCreateTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        user.setStatus(1);
        userService.insert(user);
        return new FantasyResult(FantasyResultCode.SUCCESS,user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        String result = "logout";
        currentUser.logout();
        return result;
    }
}

