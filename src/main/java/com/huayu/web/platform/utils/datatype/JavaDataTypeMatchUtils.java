package com.huayu.web.platform.utils.datatype;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.huayu.web.platform.bo.DataResourceColumn;

public class JavaDataTypeMatchUtils implements DataTypeConstants {
	public static String matchMySql(String dataType) {
		if (dataType.contains("char")) {
			return JAVA_STRING;
		} else if (dataType.contains("tinyint")) {
			return JAVA_BYTE;
		} else if (dataType.contains("int")) {
			return JAVA_LONG;
		} else if (dataType.contains("date")) {
			return JAVA_DATE;
		} else if(dataType.contains("decimal")){
			return JAVA_BIG_DECIMAL;
		}
		return JAVA_STRING;
	}
	
	public static String matchImport(String dataType){
		if (dataType.contains("date")) {
			return JAVA_DATE_IMPORT;
		} else if(dataType.contains("decimal")){
			return JAVA_BIG_DECIMAL_IMPORT;
		}
		return "" ;
	}
	
	public static Set<String> collectImportBOM(List<
			DataResourceColumn> cList){		
		Set<String> importSet = new HashSet<String>();
		for(DataResourceColumn col : cList){
			importSet.add(matchImport(col.getDataType()));
		}
		return importSet ;
	}
	
	public static String sqlTypeToJDBC(String sqlType){
		if(StringUtils.isEmpty(sqlType)){
			return "";
		}
		
		if( "int".equals(sqlType)){
			return "INTEGER" ;
		}
		
		return sqlType.toUpperCase() ;
	}
	private static Map<String , String> jdbcMap = new HashMap<String,String>();
	static {
		jdbcMap.put("int", Integer.class.getName());
	}
}
