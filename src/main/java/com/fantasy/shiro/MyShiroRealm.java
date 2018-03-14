package com.fantasy.shiro;

import com.fantasy.user.model.Role;
import com.fantasy.user.model.User;
import com.fantasy.user.model.UserPermission;
import com.fantasy.user.service.IRoleService;
import com.fantasy.user.service.IUserPermissionService;
import com.fantasy.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by fantasy on 2017/12/23.
 * 用户登录认证授权
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserPermissionService userPermissionService;

    /**
     * 认证
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username",username);
        map.put("password",password);
        User user = null;
        List<User> userList = userService.selectByMap(map);
        if(userList.size() != 0){
            user = userList.get(0);
        }
        if (null == user){
            throw new AccountException("用户名或密码错误");
        }else if(user.getStatus() == 0){
            throw new DisabledAccountException("该用户禁止登录");
        }else{
            //更新登录时间
            user.setLastLoginTime(new Date());
            userService.updateById(user);
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User token = (User)SecurityUtils.getSubject().getPrincipal();
        Long userId = token.getId();
        SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //根据id查询角色
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",userId);
        List<Role> roleList = roleService.selectByMap(map);
        Set<String> roleSet = new HashSet<String>();
        for(Role role : roleList){
            roleSet.add(role.getType());
        }
        info.setRoles(roleSet);
        List<UserPermission> permissionList = userPermissionService.selectByMap(map);
        Set<String> permissonSet = new HashSet<String>();
        for(UserPermission permission : permissionList){
            permissonSet.add(permission.getUrl());
        }
        info.setStringPermissions(permissonSet);
        return info;
    }

}
