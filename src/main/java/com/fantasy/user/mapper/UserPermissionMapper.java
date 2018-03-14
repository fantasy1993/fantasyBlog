package com.fantasy.user.mapper;

import com.fantasy.user.model.UserPermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
public interface UserPermissionMapper extends BaseMapper<UserPermission> {

    @Select("select url,permission_init as permissionInit,sort from user_permission")
    List<UserPermission> selectAll();
}
