package com.fantasy.user.service.impl;

import com.fantasy.user.model.Role;
import com.fantasy.user.mapper.RoleMapper;
import com.fantasy.user.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
