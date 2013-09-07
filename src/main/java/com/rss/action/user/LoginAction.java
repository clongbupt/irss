package com.rss.action.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rss.action.BaseAction;
import com.rss.pojos.User;
import com.rss.service.user.LoginService;
 
/**
 * LoginAction 登录控制器
 * 
 * ************************************************
 * @value user         user对象
 * 
 * @value userName     用户名字(如：吴昌龙)，从页面获得
 * @value password     用户密码(如：******)，从页面获得
 * 
 * @value userService  user相关底层方法调用，具体参见UserService注释
 * 
 * **************************************************************************
 * @return SUCCESS     转跳到登录成功页面
 * @return INPUT       转跳到登录页面
 * 
 * @function login()   支撑登录页面  
 * 
 * **************************************************************************
 * @author clong
 * @date  2011-11-30
 */

@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
	private static final Log log = LogFactory.getLog(LoginAction.class);
	 

	private User user;//数据库里面user对象
	private LoginService service;
	private String userName;//页面传来的userName，需要和jsp页面对应值严格一样
	private String password;//页面传来的password，需要和jsp页面对应值严格一样
	
	public String login() throws Exception {
		System.out.println("user:"+getUserName()+"    password:"+getPassword());
		
		//从页面获得用户名和密码
		
		if (isInvalid(getUserName())) {      //是否为空判断
            this.addActionError("用户名不能为空！");
            return INPUT;      //登录失败，转跳到登录页面
        }
        if (isInvalid(getPassword())) {
            this.addActionError("密码不能为空!");
            return INPUT;
        }
        
        //调用底层方法获取用户数据       
        setUser(getService().login(getUserName(),getPassword()));
        
        if(user != null){   	 //如果user不为空
            getSession().put("username", getUserName());   //将username压入session
            getSession().put("userid", user.getUserId());  //将userid压入session
            log.debug("登陆成功【用户名："+session.get("username")+"】");
            return SUCCESS;      //如果登录成功，转跳到阅读页面
        }else{
        	log.debug("登陆验证失败【用户名："+getUserName()+"】");
        	
            return INPUT;
        }
	}
    
    
    private boolean isInvalid(String value){
    	return (value == null || value.length() == 0);
    }
	
	public User getUser(){
    	return user;
    }
    public void setUser(User user){
    	this.user=user;
    }
    public LoginService getService() {
		return service;
	}
	public void setService(LoginService service) {
		this.service = service;
	}
    public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return this.userName;
	}
    public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
    
}