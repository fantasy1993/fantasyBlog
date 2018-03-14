package com.fantasy.user.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
@TableName("user_permission")
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * url地址
     */
	private String url;
    /**
     * url描述
     */
	@TableId(value="permission_init")
	private String permissionInit;
	/**
	 * 排序
	 */
	private Integer sort;

	public String getPermissionInit() {
		return permissionInit;
	}

	public void setPermissionInit(String permissionInit) {
		this.permissionInit = permissionInit;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "UserPermission{" +
			"id=" + id +
			", url=" + url +
			", permissionInit=" + permissionInit +
			"}";
	}
}
