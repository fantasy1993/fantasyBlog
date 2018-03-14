package com.fantasy.user.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fantasy
 * @since 2017-12-23
 */
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private Long uid;
    /**
     * 角色ID
     */
	private Long rid;


	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	@Override
	public String toString() {
		return "UserRole{" +
			"uid=" + uid +
			", rid=" + rid +
			"}";
	}
}
