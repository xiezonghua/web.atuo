package com.huayu.web.platform.db.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.db.BasicDbManager;
import com.huayu.web.platform.db.bo.DataResourceTable;

public class DbSourceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(DbSourceService.class.getCanonicalName());
	
	public List<DataResourceTable> getDatabaseTables(String sqlUrl, String userName, String password , String dbName) {
		BasicDbManager dbManager = new BasicDbManager(sqlUrl, userName, password);
		String sqlStr = "SELECT table_name tableName, table_comment tableComment FROM information_schema.`TABLES` WHERE table_schema = ?" ;
		LOGGER.debug("sql : {}" , sqlStr);
		List<Object> params = new ArrayList<Object>();
		params.add(dbName);
		return dbManager.selectList(sqlStr, params, DataResourceTable.class);
	}

}
