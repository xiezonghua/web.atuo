package com.huayu.web.platform.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class BasicUserAction extends ActionSupport {

	private static final long serialVersionUID = 6800744849910198243L;

	private String loginName;
	private String userName;
	private String userId;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
