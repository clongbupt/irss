package com.rss.action.search;

import java.util.ArrayList;
import java.util.List;

import com.rss.action.BaseAction; 
import com.rss.dao.FeedDAO;
import com.rss.pojos.Feed;
import com.rss.pojos.FeedItem;
import com.rss.service.read.ReadService;
import com.rss.service.search.HistorySearch;

@SuppressWarnings("serial")
public class SearchAction extends BaseAction{
	/**
	 * 
	 */
	private String keyword;
	private HistorySearch historySearch;
	private int userId;
	private List<FeedItem> feedItemList;
	private List<String> feedTitleList;
	private FeedDAO feedDAO;
	
	private ReadService readService;
	
	@SuppressWarnings("unchecked")
	public String search() throws Exception{
		
		if(!this.getSession().containsKey("userid"))    //没有登录
		{
			userId = 1;//默认的自由访客ID
            this.getSession().put("userid", userId);     //将'1'(自由访客)压入session
		}else {
			this.userId = (Integer)this.getSession().get("userid");    //登录，则取得userid
		}
		if(isInvalid(getKeyword())){
			this.addActionError("搜索栏不能为空！");
		}
		
		System.out.println("keyword : " + getKeyword());
		
		//this.getSession().put("keyword", keyword);
		
		System.out.println("userId : " + userId);
		
		setFeedItemList(historySearch.search(userId,keyword));

		feedTitleList = new ArrayList<String>();
		
		for(FeedItem feedItem : feedItemList){
			Feed feed = feedDAO.findById(feedItem.getFeedId());
			System.out.println(feed.getFeedTitle());
			if (!feed.getFeedTitle().isEmpty())
				feedTitleList.add(feed.getFeedTitle());
			else{
				feedTitleList.add("来源未知");
			}
		}
		System.out.println("here success and number is : " + feedItemList.size());
		
		return "success";
	}
	
	/**
	 * 提供搜索查看详细页面
	 * @param feedItemId 通过该值查到该文章，以及该文章的来源
	 *
	 * @param 返回首页需要记录的参数
	 * @param 浏览下一篇文章需要
	 */

	private boolean isInvalid(String keyWord) {
		// TODO Auto-generated method stub
		if(keyWord == null || keyWord.length() == 0)
			return false;
		else
			return true;
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setHistorySearch(HistorySearch historySearch) {
		this.historySearch = historySearch;
	}

	public HistorySearch getHistorySearch() {
		return historySearch;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setFeedItemList(List<FeedItem> feedItemList) {
		this.feedItemList = feedItemList;
	}

	public List<FeedItem> getFeedItemList() {
		return feedItemList;
	}

	public void setFeedDAO(FeedDAO feedDAO) {
		this.feedDAO = feedDAO;
	}

	public FeedDAO getFeedDAO() {
		return feedDAO;
	}

	public void setFeedTitleList(List<String> feedTitleList) {
		this.feedTitleList = feedTitleList;
	}

	public List<String> getFeedTitleList() {
		return feedTitleList;
	}

	public void setReadService(ReadService readService) {
		this.readService = readService;
	}

	public ReadService getReadService() {
		return readService;
	}
}