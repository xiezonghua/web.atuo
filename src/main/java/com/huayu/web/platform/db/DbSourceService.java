package com.huayu.web.platform.db;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.bo.DataResourceColumn;
import com.huayu.web.platform.bo.DataResourceTable;

public class DbSourceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(DbSourceService.class.getCanonicalName());
	
	public List<DataResourceTable> getDatabaseTables(String sqlUrl, String userName, String password , String dbName) {
		BasicDbManager dbManager = getDbManager(sqlUrl, userName, password); 
//		execute(dbManager);
		return getDatabaseTables(dbManager , dbName);
	}
	
	public List<DataResourceTable> getDatabaseTables(BasicDbManager dbManager , String dbName) {
		String sqlStr = "SELECT table_name tableName, table_comment tableComment FROM information_schema.`TABLES` WHERE table_schema = ?" ;
		LOGGER.debug("sql : {}" , sqlStr);
		List<Object> params = new ArrayList<Object>();
		params.add(dbName);
		return dbManager.selectList(sqlStr, params, DataResourceTable.class);
	}
	
	public List<DataResourceColumn> getDatabaseColumn(String sqlUrl , String userName , String password ,String dbName , String tableName){
		BasicDbManager dbManager = getDbManager(sqlUrl, userName, password); 
		return getDatabaseColumn(dbManager, dbName , tableName);
	}
	
	public List<DataResourceColumn> getDatabaseColumn(BasicDbManager dbManager ,String dbName , String tableName){
		String sqlStr = "SELECT column_name columnName , column_default columnDefault, " +
				"	data_type dataType , column_comment columnComment , column_key columnKey FROM information_schema.`COLUMNS` WHERE table_schema=? AND table_name=?";
		LOGGER.debug("sql:{}" , sqlStr);
		List<Object> params = new ArrayList<Object>();
		params.add(dbName);
		params.add(tableName);
		return dbManager.selectList(sqlStr, params, DataResourceColumn.class);
	}
	
	private BasicDbManager getDbManager(String sqlUrl, String userName, String password){
		return new BasicDbManager(sqlUrl, userName, password);	
	}
	
	public void execute(BasicDbManager dbManager ){
		String sqlStr = "insert into tb_user ( yhm , pwd , email , nic , name ) values ( ? , ? , ? , ? , ? )";
		List<Object> params = new ArrayList<Object>();
		params.add("mail@163.com");
		params.add("fcea92f7412b5da7be0cf42b8c93759");
		params.add("mail@163.com");
		params.add("次饿的");
		params.add("mail@163.com");
		dbManager.execute(sqlStr, params);
	}
	
}
