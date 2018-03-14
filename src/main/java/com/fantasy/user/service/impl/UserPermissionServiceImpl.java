package com.fantasy.user.service.impl;

import com.fantasy.user.model.User;
import com.fantasy.user.model.UserPermission;
import com.fantasy.user.mapper.UserPermissionMapper;
import com.fantasy.user.service.IUserPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
@Service
public class UserPermissionServiceImpl extends ServiceImpl<UserPermissionMapper, UserPermission> implements IUserPermissionService {
    @Autowired
    UserPermissionMapper userPermissionMapper;

    @Override
    public List<UserPermission> selectAll() {
        return userPermissionMapper.selectAll();
    }
}
