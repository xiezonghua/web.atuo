package com.huayu.web.platform.service;

import org.apache.velocity.VelocityContext;

import com.huayu.web.platform.bo.DataResourceTable;
import com.huayu.web.platform.constant.AutoClassInfoConstant;
import com.huayu.web.platform.constant.AutoDataResourceInfoConstant;
import com.huayu.web.platform.utils.string.CamelCaseUtils;

public class WebAutoContext extends VelocityContext {
	
	public void loadContent(DataResourceTable table){
		loadClassContent(table);
		 
		loadDbContent(table);
	}
	
	private void loadClassContent(DataResourceTable table){
		put(AutoClassInfoConstant.PACKAGE_NAME, "com.huayu");
		String camelTableName = toCamelCase(table.getTableName());
		put(AutoClassInfoConstant.NAME , toUpperCaseStart(camelTableName));
		put(AutoClassInfoConstant.NAME_LOWER_CASE, camelTableName);
		put(AutoClassInfoConstant.COMMENT , table.getTableComment());		
		put(AutoClassInfoConstant.PROPERTY_LIST, table.getColumns());		
	}

	private void loadDbContent(DataResourceTable table){
		put(AutoDataResourceInfoConstant.TABLE_NAME , table.getTableName());
	}
	
	private String toCamelCase(String obj){	
		return CamelCaseUtils.toCamelCase(obj,1);
	}
	
	private String toUpperCaseStart(String camelCase){
		char[] camel = camelCase.toCharArray();
		camel[0] = Character.toUpperCase(camel[0]);		
		return new String(camel);
	}
}
