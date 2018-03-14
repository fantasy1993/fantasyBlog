package com.fantasy.user.service.impl;

import com.fantasy.user.model.User;
import com.fantasy.user.mapper.UserMapper;
import com.fantasy.user.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
