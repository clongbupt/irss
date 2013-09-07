package com.rss.service.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rss.dao.UserDAO;
import com.rss.pojos.User;

public class ModifyService {
	private UserDAO userdao;
//	private User user;
    public UserDAO getUserdao() {
		return userdao;
	}
 
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	 
	public void modifyinfo(User user,String email,String email_password){
//	    user.setEmail(email);
//	    user.setEmail_password(email_password);
//	    userdao.update(user);
    }
	
	public Boolean updatepassword(int userId,String oldpwd,String newpwd)
	{
		User user=userdao.findById(userId);
	    if(user.getPassword().equals(oldpwd))
	    {
	    	user.setPassword(newpwd);
	    	userdao.update(user);
	    	return true;
	    }
	    else return false;   
	}

}
