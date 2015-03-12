package com.huayu.web.model;

import com.huayu.web.platform.web.action.BasicModel;

public class DbResourceModel extends BasicModel {
	private String sqlUrl;
	private String userName;
	private String password;

	public String getSqlUrl() {
		return sqlUrl;
	}

	public void setSqlUrl(String sqlUrl) {
		this.sqlUrl = sqlUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
