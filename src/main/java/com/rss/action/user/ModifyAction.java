package com.rss.action.user;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.rss.action.BaseAction;
import com.rss.pojos.User;
import com.rss.service.user.ModifyService;


public class ModifyAction extends BaseAction{
    private static final long    serialVersionUID    = 1L;
    private String email;
    private String email_password;
    private String oldpwd;
    private String password1;
    private ModifyService service;
    private int userId;
    
    
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_password() {
		return email_password;
	}
	public void setEmail_password(String email_password) {
		this.email_password = email_password;
	}
	public ModifyService getService() {
		return service;
	}
	public void setService(ModifyService service) {
		this.service = service;
	}
	
	//修改个人资料
	public String modifyinfo() throws Exception {
		User user=(User)session.get("user");
		getService().modifyinfo(user,getEmail(),getEmail_password());
        return "modifyinfosucc";
    }
	
	//修改密码
	public String modifypwd() throws Exception {
		User user=(User)session.get("user");
		if(getService().updatepassword(user.getUserId(),getOldpwd(),getPassword1()))
		{
			return "modifypwdsucc";
		}
		else
		{
			this.addActionError("原密码错误，请重新输入!");
            return "modifypwdfail";
		}
    }
	
	//添加一些验证输入是否为正确格式的函数
	private boolean isInvalidEmail(String value) {
        return (value == null || value.length() == 0);
    }


}