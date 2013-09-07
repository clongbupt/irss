package com.rss.action.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.rss.action.BaseAction;
import com.rss.service.user.RegisterService;

/**
 * LogoutAction  登出控制器 
 * *********************************************
 * @author clong
 * @date  2011-11-30
 */
 
public class ValidateAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private RegisterService registerService;
	
	
	public String registerUserName(){
		
		//处理获得的参数
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String username = request.getParameter("userName");
		
		System.out.println("Ajax register userName:" + username);
		 
		if (registerService.checkUserByUserName(username))
			return renderText("false");
		else
			return renderText("true");
	} 
	
	public String registerEmail(){
		
		//处理获得的参数
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String email = request.getParameter("email");
		
		System.out.println("Ajax email:" + email);
		 
		if (registerService.checkUserByEmail(email)){
			System.out.println("\n\nfalse\n\n");
			return renderText("false");
		
		}
		else{
			System.out.println("\n\ntrue\n\n");
			return renderText("true");
		}
	} 
	
	public String loginUserName(){
		
		//处理获得的参数
		HttpServletRequest request = ServletActionContext.getRequest(); 
		String username = request.getParameter("userName");
		
		System.out.println("Ajax login userName:" + username);
		 
		if (registerService.checkUserByUserName(username))
			return renderText("true");
		else
			return renderText("false");
	} 
	
	/** 
     * 直接输出字符串.  
     */  
     protected String renderText(String text) {  
        return render(text, "text/plain;charset=UTF-8");  
     }
     
     protected String render(String text, String contentType){  
         try{  
             HttpServletResponse response = ServletActionContext.getResponse();  
             response.setContentType(contentType);  
             response.addHeader("Pragma", "no-cache");  
             response.setHeader("Cache-Control", "no-cache");
             response.getWriter().write(text);  
             response.getWriter().flush(); 
            }  
            catch (IOException e) {  
            }  
            return null;  
         }
	
	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

}
