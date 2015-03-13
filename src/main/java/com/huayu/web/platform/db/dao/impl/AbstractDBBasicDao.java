package com.huayu.web.platform.db.dao.impl;

import java.io.Serializable;
import java.util.List;

import com.huayu.web.platform.db.dao.DaoMapper;

public abstract class AbstractDBBasicDao<T , ID extends Serializable>  {
/*	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}*/

	public abstract DaoMapper<T , ID> getDaoMapper();
	
	
	public int delete(ID id){
		return getDaoMapper().deleteByPrimaryKey(id);
	}
	
	public int add(T obj){
		return getDaoMapper().insert(obj);
	}

	public int addSelective(T obj){
		return getDaoMapper().insertSelective(obj);
	}

	public T queryByPrimaryKey(ID id){
		return getDaoMapper().selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(T obj){
		return getDaoMapper().updateByPrimaryKey(obj);
	}

	public int updateByPrimaryKey(T record){
		return getDaoMapper().updateByPrimaryKey(record);
	}

	public long count(){
		return getDaoMapper().count();
	}

	public List<T> queryAll(){
		return getDaoMapper().selectAll();
	}
}
