package com.rss.pojos;

public class Feed {
	
	private int feedId; 
	private String feedUrl;
	private String feedTitle;
	private String feedDescription;
	private String feedLastTime;
	private Integer level;
	
	  
	
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getFeedUrl() {
		return feedUrl;
	}
	public void setFeedUrl(String feedUrl) {
		this.feedUrl = feedUrl;
	}
	public String getFeedTitle() {
		return feedTitle;
	}
	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}
	public String getFeedDescription() {
		return feedDescription;
	}
	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}
	public String getFeedLastTime() {
		return feedLastTime;
	}
	public void setFeedLastTime(String feedLastTime) {
		this.feedLastTime = feedLastTime;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public static void main (String args[]){
		
		Feed feed = new Feed();
		
		feed.setFeedDescription("Hello World!");
		
		System.out.println(feed.getFeedDescription());
	}
	
}
