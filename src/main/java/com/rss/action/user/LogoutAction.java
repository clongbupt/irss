package com.rss.action.user;

import javax.servlet.ServletException;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.rss.action.BaseAction;

/**
 * LogoutAction  登出控制器
 * *********************************************
 * @author clong
 * @date  2011-11-30
 */

public class LogoutAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	public String logout() throws  ServletException {
		
		System.out.println("准备退出啦！！！！！！！！！！！！！！！！！！！！！！！");
		
		//清理session
		
		this.getSession().clear();
		//ActionContext.getContext().getSession().clear();
		//ServletActionContext.getRequest().getSession().invalidate();
		System.out.println("退出啦！！！！！！！！！！！！！！！！！！！！！！！");
		  
		return SUCCESS;
	} 
}
