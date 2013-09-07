package com.rss.action.user;

import com.rss.action.BaseAction;
import com.rss.interceptor.CheckLoginInterceptor;
import com.rss.pojos.User;
import com.rss.service.user.RegisterService;

/**
 * RegisterAction 注册控制器
 * 
 * ****************************************************
 * @value user
 * 
 * @value userName
 * @value password
 * @value email
 * @value avtivate        
 * 
 * @value userService
 * 
 * ****************************************************
 * @author clong
 *
 */

@SuppressWarnings("serial")
public class RegisterAction extends BaseAction{
	
	private String userName ="";
	private String password ="";
	private String email = "";
	private char activate;
	
	private RegisterService registerService;
	
	private String REGISTER = "register";
	
	public String register() throws Exception {
		
		System.out.println("userName:"+getUserName()+"    password:"+getPassword()+"	email:"+getEmail());
//		if (isInvalid(getUserName())) {
//            this.addActionError("用户名为空!");
//            return REGISTER;
//        }
//        if (isInvalid(getPassword())) {
//            this.addActionError("密码为空!");
//            return REGISTER;
//        }
//        if (isInvalid(getEmail())) {
//            this.addActionError("邮箱为空!");
//            return REGISTER;
//        }
         
        	if ((!userName.equals("")) && (!password.equals("")) && (!email.equals(""))){
        		 
        		registerService.register(userName,password,email,'0');
	        	
	        	User user = registerService.getUserByUserName(userName);
	        	
	        	getSession().put("username", getUserName());   //将username压入session
	            getSession().put("userid", user.getUserId());  //将userid压入session
	            
	            if (registerService.sendMail(userName, password, email))
	            	System.out.println("邮件发送成功！");
	        	
	        	addActionMessage("注册成功!");
	            return SUCCESS;
        	}
        	else
        		return REGISTER;	

        
	}
    
    
    private boolean isInvalid(String value){
    	return (value == null || value.length() == 0);
    }
    
 
    public RegisterService getRegisterService() {
		return registerService;
	}
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	
    public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	public void setEmail(String email){
		this.email = email;
	}
	 
	public String getEmail(){
		return this.email;
	}
	
	public void setActivate(char activate){
		this.activate = activate;
	}
	
	public char getActivate(){
		return this.activate;
	}
    
}
