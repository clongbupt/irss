package com.rss.pojos;

public class UserItemRelation{
	
	private int userItemId;
	private int userId;
	private int feedItemId;
	private float score;
	
	 
	public int getUserItemId() { 
		return userItemId; 
	}
	public void setUserItemId(int userItemId) {
		this.userItemId = userItemId;
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
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}


}
