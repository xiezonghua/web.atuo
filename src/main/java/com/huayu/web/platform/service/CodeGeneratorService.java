package com.huayu.web.platform.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.bo.ClassGenInfo;
import com.huayu.web.platform.bo.DataResourceColumn;
import com.huayu.web.platform.bo.DataResourceTable;
import com.huayu.web.platform.constant.AutoClassInfoConstant;
import com.huayu.web.platform.constant.ClassTempleteEnum;
import com.huayu.web.platform.db.DbSourceService;
import com.huayu.web.platform.factory.BasicAutomationFactory;

public class CodeGeneratorService {
	private final static Logger LOGGER = LoggerFactory.getLogger(CodeGeneratorService.class.getCanonicalName());
	
	private DbSourceService dbSource = new DbSourceService();
	
	public void generateCode(String sqlUrl , String userName , String password , String dbName){
		LOGGER.info("Generate path: {}" ,AutoClassInfoConstant.STORE_SRC_PATH);
		clean();
		
		List<DataResourceTable> databaseTables = dbSource.getDatabaseTables(sqlUrl, userName, password, dbName);
		LOGGER.debug("query the tables from {} success , data size is {}" , dbName , databaseTables.size());
		
		for (DataResourceTable table : databaseTables) {
			List<DataResourceColumn> columns = dbSource.getDatabaseColumn(sqlUrl, userName, password, dbName, table.getTableName());
			
			LOGGER.debug("query the table({}) information, columns size is {} " , table.getTableName() , columns.size());
			table.setColumns(columns);
			
			WebAutoContext context = new WebAutoContext();
			context.loadContent(table);
			
			generateMapperCode(context);
			generateBoCode(context);
			generateDaoCode(context);
			generateServiceCode(context);
		}
	}

	public void generateMapperCode(WebAutoContext context){
		generate(context , ClassTempleteEnum.IBATIS_MAPPING_XML);
		
		generate(context , ClassTempleteEnum.IBATIS_MAPPING_BASE_XML);
		
		generate(context , ClassTempleteEnum.IBATIS_MAPPING_IMPL);
	}
	
	public void generateBoCode(WebAutoContext context){
		generate(context, ClassTempleteEnum.BO);
		
		generate(context, ClassTempleteEnum.BO_BASE);
	}
	
	public void generateServiceCode(WebAutoContext context){
		generate(context, ClassTempleteEnum.SERVICE);
		
		generate(context, ClassTempleteEnum.SERVICE_IMPL);
	}
	
	public void generateDaoCode(WebAutoContext context){
		generate(context, ClassTempleteEnum.DAO);
		
		generate(context, ClassTempleteEnum.DAO_IMPL );
	}
	
	private void generate(WebAutoContext context , ClassTempleteEnum templeteType){
		
		String className = context.get(AutoClassInfoConstant.NAME)+ templeteType.getClassSuffix();
		LOGGER.debug("create code file , {}" , className);
		ClassGenInfo classInfo = new ClassGenInfo();
		classInfo.setClassFileName(className);
		classInfo.setTempleteOrigin(templeteType.getOrigin());
		classInfo.setClassStorePath(AutoClassInfoConstant.STORE_SRC_PATH + templeteType.getPackagePath());
		
		generate(context, classInfo);
	}
	
	private void generate(WebAutoContext context, ClassGenInfo classInfo){
		VelocityEngine velocity = BasicAutomationFactory.getVelocityInstance();
		Template template = velocity.getTemplate(classInfo.getTempleteOrigin());
		FileWriter writer = null;
		try {
			File file = new File(classInfo.getClassFullPath());
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
			LOGGER.debug(classInfo.getClassFullPath() + " create failure");
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
	
	private void clean(){
		cleanTmpPath(AutoClassInfoConstant.STORE_SRC_PATH + ClassTempleteEnum.BO.getPackagePath());	
		
		cleanTmpPath(AutoClassInfoConstant.STORE_SRC_PATH + ClassTempleteEnum.DAO.getPackagePath());	
		
		cleanTmpPath(AutoClassInfoConstant.STORE_SRC_PATH + ClassTempleteEnum.SERVICE.getPackagePath());	
		
		cleanTmpPath(AutoClassInfoConstant.STORE_SRC_PATH + ClassTempleteEnum.IBATIS_MAPPING_XML.getPackagePath());		
	}
	
	private void cleanTmpPath(String path){
		try{
			if(deleteDir(new File(path))){
				LOGGER.info("The temp path of code  clean success  , {}." , path);			
			}else{
				LOGGER.info("The temp path of code  clean failure  , {}." , path);
			}
		}catch(Exception ex){
			LOGGER.info("The temp path of code  clean failure , {}. ; because {}" , path , ex.getMessage());
		}
		
	}
	
	private static boolean deleteDir(File dir) {
		try {
			FileUtils.deleteDirectory(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (dir.isDirectory()) {
//			String[] children = dir.list();
//			for (int i = 0; i < children.length; i++) {
//				boolean success = deleteDir(new File(dir, children[i]));
//				if (!success) {
//					return false;
//				}
//			}
//		}
		// 目录此时为空，可以删除
//		return dir.delete();
		return true;
	}
}
