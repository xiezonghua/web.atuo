package com.huayu.web.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthCheckFilter extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;
	
	private String excludedPatter = "" ;
	
/*	@Resource
	private AccountService accountService ;*/
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext context = invocation.getInvocationContext() ;		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");				
		boolean isCorrect = isCorrectAccount(userName, password);
		
		invocation.invoke();
		return null;
	}
	
	private boolean isCorrectAccount(String userName , String password){
		boolean  isCorrect = false ;
//		UserInfo user = accountService.queryUser(userName, password);		
//		if( null != user ){
//			isCorrect = true ;			
//		}		
		return isCorrect ;
	}

}
