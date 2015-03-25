package com.huayu.web.platform.session;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.Cookie;

public interface Session {
	public void put(String name, Serializable o);	
	public Object get(String name);
	public Object getAttribute(String name) ;	
	public void setAttribute(String name, Serializable o) ;
	public void removeAttribute(String name) ;
	public String getUUID();
	public Cookie getCookie();	
	public void removeAll();
	
	Map getAll();
}
