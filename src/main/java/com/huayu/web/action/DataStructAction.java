package com.huayu.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.huayu.web.model.DbResourceModel;
import com.huayu.web.platform.web.action.BasicModel;
import com.huayu.web.platform.web.action.BasicModelAction;

@Namespace("/db/resource")
public class DataStructAction extends BasicModelAction {
	private static final long serialVersionUID = 6297799815412762871L;

	String sqlUrl = "jdbc:mysql://192.168.69.80:3306/app";
	String userName = "xzh";
	String password = "xzh";
	
	private DbResourceModel model = new DbResourceModel();
	
	@Override
	public BasicModel getModel() {
		return model;
	}
	
	@Action(value="tables" , results={@Result(name=SUCCESS ,type="json")})
	public String tables(){
		model.setPassword("add");
		model.setSqlUrl("sqlUrl");
		return SUCCESS;
	}

}
