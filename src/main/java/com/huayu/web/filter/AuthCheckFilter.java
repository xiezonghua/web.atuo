package com.huayu.web.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthCheckFilter extends AbstractInterceptor implements Filter{

	private static final long serialVersionUID = 1L;
	
	private String excludedPatter = "" ;
	
/*	@Resource
	private AccountService accountService ;*/
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Pattern pathPatten = Pattern.compile("");
		Matcher matcher = pathPatten.matcher("");
		if(matcher != null && matcher.find()){
			invocation.invokeActionOnly();
			return null;
		}
		ActionContext context = invocation.getInvocationContext() ;		
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");				
		boolean isCorrect = isCorrectAccount(userName, password);
		if(isCorrect){
			invocation.invoke();
		}
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

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
