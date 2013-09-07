package com.rss.service.user; 

import com.rss.dao.UserDAO;
import com.rss.pojos.User;
import com.rss.service.tools.SendMail;
 
public class  RegisterService{
	private UserDAO userdao;
 
	public void register(String userName,String password,String email,char activate){
		User user = new User();
		
		user.setUserName(userName);  
		user.setPassword(password);
		user.setEmail(email);
		user.setActivate(activate);
		
		userdao.save(user);
    } 
	 
	public User getUserByUserName(String userName){
		
		User user = new User();
		
		user = (User)userdao.findByUsername(userName).get(0);
		 
		return user;
	}	
	
	public boolean checkUserByUserName (String userName){
		
		User user = new User(); 
		 
		if (!userdao.findByUsername(userName).isEmpty()){
			user = (User)userdao.findByUsername(userName).get(0);
			return true; 
		}
		else
			return false;
	}
	     
	public boolean checkUserByEmail (String email){
		
		User user = new User();
		if (!userdao.findByEmail(email).isEmpty()){
			user = (User)userdao.findByEmail(email).get(0);
			System.out.println("\n\nhello\n\n"); 
			return true;
		} 
		else{
			System.out.println("\n\nuser is null\n\n"); 
			return false;
		}
	}
	
	public boolean sendMail (String userName,String password,String mail){
		 
		SendMail sm = new SendMail(userName,password,mail);
		
		sm.sendEmail();
		
		return true;
	}
	
	
    public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}
	

}