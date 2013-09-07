/*************************************************
 @Copyright (C), 2008, lzpeng 
 @File name:      CheckLoginInterceptor.java
 @Author:         lzpeng
 @CreateDate:     2008-6-17 
 @Description:    CheckLoginInterceptor������ 
 @Extends:        AbstractInterceptor   
 
 @Function List: 
 1. public String intercept()
 *************************************************/

package com.rss.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rss.action.user.LoginAction;
import com.rss.pojos.User;

public class CheckLoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	public static final String USER_SESSION_KEY="username";  

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("begin check login interceptor");

		// 对LoginAction不做该项拦截
		Object action = actionInvocation.getAction();
		if (action instanceof LoginAction) {
			System.out
					.println("exit check login, because this is login action.");
			return actionInvocation.invoke();
		}
		// 验证 session
		Map session = actionInvocation.getInvocationContext().getSession();
		User user = (User) session.get("user");

		if (user != null) {
			// 存在的情况下进行后续操作。
			System.out.println("user has already login!");
			return actionInvocation.invoke();
		} else {
			// 否则终止后续操作，返回LOGIN
			System.out.println("no login, forward login page!");

			return Action.LOGIN;

		}

	}

}
