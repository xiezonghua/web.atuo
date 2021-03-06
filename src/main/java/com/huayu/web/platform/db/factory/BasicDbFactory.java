package com.huayu.web.platform.db.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.db.constant.DbDriverConstants;

public class BasicDbFactory {
	private  static Logger logger =  LoggerFactory.getLogger(BasicDbFactory.class) ;
	
	public static Connection getConnection(String url ,  String userName , String password){
		Connection conn = null ;
		String driverName = "" ;
		if(url.contains("mysql")){
			driverName = DbDriverConstants.MYSQL_DRIVER_NAME;			
		}else if(url.contains("oracle")){
			driverName = DbDriverConstants.ORACLE_DRIVER_NAME;
		}
		
		if(driverName.isEmpty()){
			logger.error("Drive type cannot be determined");
			return null ;
		}
		try {
			Class.forName(driverName);
//			conn = DriverManager.getConnection("jdbc:mysql://192.168.68.56:3306/app?user=xzh&password=xzh");
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {			
			logger.error("Driver failed to initialize");
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Database connection failed");
		}
		return conn ;
	}
	
	public static void main(String[] args) {
		String sqlUrl = "jdbc:mysql://192.168.67.130:3306/digxy_test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&maxReconnects=2";
		String userName = "xzh";
		String password = "xzh";
		Connection con = BasicDbFactory.getConnection(sqlUrl, userName, password);
		System.out.println("=======" + con);
		
	}
}
