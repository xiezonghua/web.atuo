package com.huayu.web.platform.db.dao;

import java.io.Serializable;
import java.util.List;

/**
 * data opt basic 
 * @author v_xiezonghua
 *
 */
public interface DBBasicDao<T , ID extends Serializable> {

	public int delete(ID id);
	
	public int add(T obj);

	public int addSelective(T obj);

	public T queryByPrimaryKey(ID id);
	public int updateByPrimaryKeySelective(T obj);

	public int updateByPrimaryKey(T record);

	public long count();

	public List<T> queryAll();
	
}
