package com.rss.pojos;

public class FeedItem {
	 
	private int feedItemId;
	private int feedId;
	private String feedItemUrl; 
	private String feedItemTitle;
	private String feedItemAuthor;
	private String feedItemDescription;
	private String feedItemPubDate;
	private String feedItemCategory; 
	private Float feedItemPostRank;
	private String feedItemContent;
	
	
	public int getFeedItemId() {
		return feedItemId;
	}
	public void setFeedItemId(int feedItemId) {
		this.feedItemId = feedItemId;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getFeedItemUrl() {
		return feedItemUrl;
	}
	public void setFeedItemUrl(String feedItemUrl) {
		this.feedItemUrl = feedItemUrl;
	}
	
	public String getFeedItemAuthor() {
		return feedItemAuthor;
	}
	public void setFeedItemAuthor(String feedItemAuthor) {
		this.feedItemAuthor = feedItemAuthor;
	}
	public String getFeedItemDescription() {
		return feedItemDescription;
	}
	public void setFeedItemDescription(String feedItemDescription) {
		this.feedItemDescription = feedItemDescription;
	}
	public String getFeedItemPubDate() {
		return feedItemPubDate;
	}
	public void setFeedItemPubDate(String feedItemPubDate) {
		this.feedItemPubDate = feedItemPubDate;
	}
	public String getFeedItemCategory() {
		return feedItemCategory;
	}
	public void setFeedItemCategory(String feedItemCategory) {
		this.feedItemCategory = feedItemCategory;
	}
	public Float getFeedItemPostRank() {
		return feedItemPostRank;
	}
	public void setFeedItemPostRank(Float feedItemPostRank) {
		this.feedItemPostRank = feedItemPostRank;
	}
	public String getFeedItemContent() {
		return feedItemContent;
	}
	public void setFeedItemContent(String feedItemContent) {
		this.feedItemContent = feedItemContent;
	}
	public void setFeedItemTitle(String feedItemTitle) {
		this.feedItemTitle = feedItemTitle;
	}
	public String getFeedItemTitle() {
		return feedItemTitle;
	}
}
