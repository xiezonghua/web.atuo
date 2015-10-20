package com.huayu.web.platform.db;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huayu.web.platform.db.factory.BasicDbFactory;

public class BasicDbManager {
	private String sqlUrl;
	private String userName;
	private String password;

	private final static Logger logger = LoggerFactory.getLogger(BasicDbManager.class.getCanonicalName());
	
	private Connection conn = null;

	public BasicDbManager(String sqlUrl, String userName, String password) {
		this.sqlUrl = sqlUrl;
		this.userName = userName;
		this.password = password;
	}

	private Connection getConnection(boolean isSigal) {
		if (null == conn || isSigal) {
			conn = BasicDbFactory.getConnection(sqlUrl, userName, password);
		}
		return conn;
	}

	/**
	 * find dataList from Database  
	 * @param <T>
	 * @param sqlStr
	 * @param params
	 * @param beanClass
	 * @return
	 */
	public <T> List<T> selectList(String sqlStr, List<Object> params, Class<T> beanClass) {
		Connection con = getConnection(true);
		PreparedStatement sta = null;
		ResultSet rs = null;
		List<T> beanList = null;
		try {
			sta = con.prepareStatement(sqlStr);
			compParam(sta, params);
			rs = sta.executeQuery();
			beanList = resultSetToBean(rs, beanClass);			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, sta, rs);
		}

		return beanList == null ? new ArrayList<T>() : beanList;
	}
	
	public void execute(String sqlStr, List<Object> params) {
		Connection con = getConnection(true);
		PreparedStatement sta = null;

		try {
			sta = con.prepareStatement(sqlStr);
			compParam(sta, params);
			sta.execute();	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(con , sta , null);
		}
	}

	
	/**
	 * Parameter insert to statement
	 * @param psta
	 * @param params
	 */
	private void compParam(PreparedStatement psta, List<Object> params) {

		for (int i = 0; i < params.size(); i++) {
			try {
				psta.setObject(i+1, params.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * load result to bean list
	 * @param <T>
	 * @param rs
	 * @param beanClazz
	 * @return
	 */
	private <T> List<T> resultSetToBean(ResultSet rs, Class<T> beanClazz) {
		List<T> beanList = new ArrayList<T>();		
		Field[] field = beanClazz.getDeclaredFields();
		
		try {
			T bean = null;
			while(rs.next()){
				bean = beanClazz.newInstance();
				for(Field f : field){					
					f.setAccessible(true);				
					try{
						if(rs.findColumn(f.getName()) > 0){
							f.set(bean, rs.getString(f.getName()));
						}
					}catch(Exception e){
						logger.info("field({}) is not a column" , f.getName());
					}
				}
				beanList.add(bean);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();			
		}
		
		return beanList;
	}
	
	/**
	 * Release resources
	 * @param con
	 * @param sta
	 * @param rs
	 */
	private void close( Connection con , Statement sta , ResultSet rs){
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != sta) {
				sta.close();
			}
			if (null != con) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("Release resources failure");
		}
	}
}
