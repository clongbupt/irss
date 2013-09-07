package com.rss.service.user;

 
import com.rss.dao.UserDAO;
import com.rss.pojos.User;

public class  LoginService{
	private UserDAO userdao;
    public UserDAO getUserdao() {
		return userdao;
	} 
 
	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	public  User login(String username,String password){
		//�û��˻��������û���Ҳ������email
		if(userdao.findByUsername(username).size()!=0)
		{
			User user=(User)userdao.findByUsername(username).get(0);
		    if(user.getPassword().equals(password)) 
		    	return user;
		    else
		    	return null;
		}
		else if(userdao.findByEmail(username).size()!=0)
		{
			User user=(User)userdao.findByEmail(username).get(0);
		    if(user.getPassword().equals(password)) 
		    	return user;
		    else { 
				return null;
			}
		}
		else return null;
    } 
	
}
