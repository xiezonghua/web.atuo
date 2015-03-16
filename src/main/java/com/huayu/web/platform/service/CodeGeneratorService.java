package com.huayu.web.platform.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.bo.ClassGenInfo;
import com.huayu.web.platform.factory.BasicAutomationFactory;

public class CodeGeneratorService {
	private final static Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorService.class.getCanonicalName());
	public void generateCode(String sqlUrl , String userName , String password){
		
	}
	
	public void generateDao(WebAutoContext context , String className){
		ClassGenInfo classInfo = new ClassGenInfo();
		classInfo.setClassName(className);
		classInfo.setTempleteOrigin("");
		classInfo.setClassStorePath("");
		
		generate(context, classInfo);
	}
	
	private void generate(WebAutoContext context, ClassGenInfo classInfo){
		VelocityEngine velocity = BasicAutomationFactory.getVelocityInstance();
		Template template = velocity.getTemplate(classInfo.getTempleteOrigin());
		FileWriter writer = null;
		try {
			File file = new File(classInfo.getClassStorePath()+ classInfo.getClassName());
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}			 
			writer = new FileWriter(file);
			template.merge(context, writer);
			writer.flush();
		} catch (IOException e) {
			LOGGER.debug("interface impl create failure");
			e.printStackTrace();
		} finally {
			try {
				if (null != writer)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
