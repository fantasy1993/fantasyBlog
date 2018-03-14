package com.fantasy.user.service;

import com.fantasy.user.model.UserPermission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
public interface IUserPermissionService extends IService<UserPermission> {
        List<UserPermission> selectAll();
}
