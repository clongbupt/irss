package com.rss.action.read;

import com.rss.action.BaseAction;
import com.rss.pojos.FeedItem;
import com.rss.service.read.ReadService;

/** 
 * ReadAction 阅读控制器
 * 
 * ************************************************
 * @value number       阅读条目数，与页面交互
 * @value feedItemId   阅读条目ID，页面交互
 * 
 * @value userId       用户ID，存入SESSION，用于记录用户特征
 * 
 * @value level        用户对阅读条目的评分(如：3表示喜欢)，从页面获得
 * 
 * @value userName     用户名字(如：吴昌龙)，页面呈现
 * @value feedItem     阅读条目(包含：标题、作者、发布时间、内容等)，页面呈现
 * @value feedTitle    阅读条目的源标题(如：36Kr)，页面呈现
 * 
 * @value readService  read相关底层方法调用，具体参见ReadService注释
 *  
 * **************************************************************************
 * @function read()    支撑用户阅读页面  
 *   
 * **************************************************************************
 * @author clong
 * @date  2011-11-30
 */

@SuppressWarnings("serial")
public class ReadAction extends BaseAction{
	
	private Integer number;
	private Integer userId;
	private String userName;
	
	private char   level;
	private Integer feedItemId;
	
	private FeedItem feedItem;
	private String   feedTitle;
	
	private ReadService readService;
	
	public String read(){
		
		//userId = 1;
		
		System.out.println("session***********userid******"+this.getSession().get("userid"));
		
		//判断session中是否存在'userid'，即：用户是否已经登录
		
		if(!this.getSession().containsKey("userid"))    //没有登录
		{
			userId = 1;//默认的自由访客ID
            this.getSession().put("userid", userId);     //将'1'(自由访客)压入session
		}else {
			this.userId = (Integer)this.getSession().get("userid");    //登录，则取得userid
		}
		
		if(!this.getSession().containsKey("username"))
		{
			setUserName("自由访客");
			getSession().put("username", getUserName());    //将'自由访客'压入session
		}else {
			setUserName(this.getSession().get("username").toString());//得到用户姓名
		}			
		System.out.println("用户名字：####################################"+userName);
		
		//判断当前用户访问到了第几个条目
		
		if (number == null)          //空表示用户访问第一个条目
			number = 1;               
		
		//判断是否有评分
		
		
		System.out.println("level：####################################"+level);
		
		if (level == '1' || level == '2' || level == '3'){         //如果有评分
			//增加一个方法查看是read表里面否有feedItemId
			//if (!readService.CheckFeedItemRead(userId, feedItemId))
			readService.addReadItem(feedItemId,userId,level);      //添加一条阅读记录
		}
		
		System.out.println("number：####################################"+number);
		System.out.println("feedItemId：####################################"+feedItemId);

		if (number > 1 || feedItemId == null)  {
			setFeedItem(readService.getFeedItem(number,userId));           //根据用户id和阅读条目获取下一条条目
		}else{    //搜索后访问详细页面
			setFeedItem(readService.getFeedItemByItemId(feedItemId));
		}
		setFeedTitle(readService.getFeedTitleByFeedId(feedItem.getFeedId()));    //根据feedid获取源标题
		
		return SUCCESS;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	public void setReadService(ReadService readService) {
		this.readService = readService;
	}
	public ReadService getReadService() {
		return readService;
	}

	public void setFeedItem(FeedItem feedItem) {
		this.feedItem = feedItem;
	}

	public FeedItem getFeedItem() {
		return feedItem;
	}

	public void setFeedItemId(Integer feedItemId) {
		this.feedItemId = feedItemId;
	}

	public Integer getFeedItemId() {
		return feedItemId;
	}

	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	public String getFeedTitle() {
		return feedTitle;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
