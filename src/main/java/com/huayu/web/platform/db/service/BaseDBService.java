package com.huayu.web.platform.db.service;

import java.util.List;

public abstract class BaseDBService<T> {
	
	public abstract T getById();
	
	public abstract List<T> getList();
}
