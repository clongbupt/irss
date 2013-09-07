package com.rss.pojos;

public class UserReadItem{
	
	private int userReadId;
	private int userId;
	private int feedItemId;
	private char level;
	private String userReadTime;
	 
	 
	public int getUserReadId() { 
		return userReadId;
	}
	public void setUserReadId(int userReadId) {
		this.userReadId = userReadId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFeedItemId() {
		return feedItemId;
	}
	public void setFeedItemId(int feedItemId) {
		this.feedItemId = feedItemId;
	}
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	public String getUserReadTime() {
		return userReadTime;
	}
	public void setUserReadTime(String userReadTime) {
		this.userReadTime = userReadTime;
	}
	

}
