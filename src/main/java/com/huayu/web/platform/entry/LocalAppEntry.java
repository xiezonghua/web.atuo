package com.huayu.web.platform.entry;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.bo.DataResourceColumn;
import com.huayu.web.platform.bo.DataResourceTable;
import com.huayu.web.platform.db.BasicDbManager;
import com.huayu.web.platform.db.DbSourceService;
import com.huayu.web.platform.utils.datatype.JavaDataTypeMatchUtils;
import com.huayu.web.platform.utils.string.CamelCaseUtils;

public class LocalAppEntry {
private final static Logger logger = LoggerFactory.getLogger(LocalAppEntry.class.getCanonicalName());
	
	private BasicDbManager dbManager  ;
	private DbSourceService service ;
	
	public LocalAppEntry(){
		String sqlUrl = "jdbc:mysql://192.168.69.80:3306/app";
		String userName = "xzh";
		String password = "xzh";
		dbManager = new BasicDbManager(sqlUrl, userName, password);
		service = new DbSourceService();
	}
	public List<DataResourceTable> querySchemaTables(String dbName){
		return service.getDatabaseTables(dbManager, dbName);
	}
	
	public List<DataResourceColumn> querySchemaColumn(String dbName , String tableName){
		return service.getDatabaseColumn(dbManager, dbName, tableName);
	}
	
	public void combine(){
		String dbName = "app";
		List<DataResourceTable> tables = querySchemaTables(dbName);
		List<DataResourceColumn> cols =  null ;
//		DaoProducer dao = new DaoProducerImpl();
//		ServiceProducer service = new ServiceProducerImpl();
//		BoProducer bo = new BoProducerImpl();
//		MapperProducer mapper = new MapperProducerImpl();
		for(DataResourceTable table: tables){
			cols = querySchemaColumn(dbName, table.getTableName());
			CustomContext context = new CustomContext() ;
			context.loadContext(table, cols);
			context.put("camelCaseTool",  CamelCaseUtils.class);
			context.put("javaMatchUtils", JavaDataTypeMatchUtils.class);
			context.put("stringUtils", StringUtils.class);
			context.put("now", new Date());
//			bo.generate(context);
//			bo.generateBase(context);
//			mapper.generate(context);
//			mapper.generateMapper(context);
//			dao.generateInterface(context);		
//			dao.generateInterfaceImpl(context);
			service.generateInterface(context);
			service.generateInterfaceImpl(context);			
		}
		
		
	}
}
