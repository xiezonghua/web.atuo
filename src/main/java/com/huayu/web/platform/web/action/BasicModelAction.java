package com.huayu.web.platform.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BasicModelAction extends ActionSupport implements
		ModelDriven<BasicModel> {
	private static final long serialVersionUID = 724115177650924555L;
	protected final Logger logger = LoggerFactory
			.getLogger(BasicModelAction.class.getCanonicalName());
	private BasicModel model = new BasicModel();
	
	
	public void setModel(BasicModel model) {
		this.model = model;
	}


	public abstract BasicModel getModel();
	
}
