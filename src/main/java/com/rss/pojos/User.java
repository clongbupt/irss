package com.rss.pojos;

public class User {
	private int userId;
	private String userName;
	private String password;
	private String email;
	private char activate;
	
	public User(){
		   
	} 
	
	public User(String userName,String password,String email,char activate){
		this.userName = userName;
		this.password = password;
		this.email = email;
    	this.activate = activate;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public int getUserId(){
		return this.userId;
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
	
	public void setEmail(String email){
		this.email = email;
	}
	 
	public String getEmail(){
		return this.email;
	}

	public void setActivate(char activate) {
		this.activate = activate;
	}

	public char getActivate() {
		return activate;
	}

	
}
