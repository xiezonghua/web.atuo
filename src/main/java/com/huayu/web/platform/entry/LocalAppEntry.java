package com.huayu.web.platform.entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.service.CodeGeneratorService;

public class LocalAppEntry {
	private final static Logger logger = LoggerFactory
			
			.getLogger(LocalAppEntry.class.getCanonicalName());
	
	private CodeGeneratorService service;
	
	private String sqlUrl = "jdbc:mysql://192.168.1.107:3306/app";
	private String userName = "xzh";
	private String password = "xzh";


	public LocalAppEntry(String sqlUrl, String userName, String password) {
		service = new CodeGeneratorService();
	}

	public void startGeneration() {
		service.generateCode(sqlUrl, userName, password, "app");
		logger.debug("Code generate success!");
	}

	public static void main(String[] args) {
//		String sqlUrl = "jdbc:mysql://192.168.1.122:3306/digxy_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=2";
		String sqlUrl = "jdbc:mysql://192.168.67.150:3306/digxy_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=2";
		String userName = "xzh";
		String password = "xzh";
		String dbName = "digxy_test";
		CodeGeneratorService codeGenService = new CodeGeneratorService();
		codeGenService.generateCode(sqlUrl, userName, password, dbName);
	}
}
