package com.rss.service.read;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rss.dao.FeedDAO;
import com.rss.dao.FeedItemDAO;
import com.rss.dao.UserItemRelationDAO;
import com.rss.dao.UserReadItemDAO;
import com.rss.pojos.Feed;
import com.rss.pojos.FeedItem;
import com.rss.pojos.UserReadItem;

public class ReadService {
	
	private FeedItemDAO feedItemDAO;
	private FeedDAO feedDAO; 

	private UserItemRelationDAO userItemRelationDAO;
	private UserReadItemDAO userReadItemDAO;

	private static final Log log = LogFactory.getLog(ReadService.class);
	
	/**
	 * 查询合适的文章，需要根据当前的userId和user_item_relation表的score第number大的值，查询feed_item表和user_item_relation表
	 * @param number
	 * @param userId
	 * @return
	 */
	
	public FeedItem getFeedItem (Integer number,Integer userId){
		
		//此处是根据老樊的推荐计算，由于暂时没有使用，先切到下面工作
		
		//UserItemRelation userItemRelation = userItemRelationDAO.findMaxScoreItemByUserId(userId,number);
		
		//Integer feedItemId = userItemRelation.getFeedItemId();
		//FeedItem feedItem = new FeedItem();
		//feedItem = feedItemDAO.findById(feedItemId);
		
		System.out.println("##############userId:####################"+userId);
		
		FeedItem feedItem = new FeedItem();
		feedItem = feedItemDAO.findMaxPostRankItem(number,userId);
		
		//System.out.println("第一个feedItemId:"+feedItem.getFeedItemId());
		 
//		int i = number;
//		while (userReadItemDAO.findByItemId(feedItem.getFeedItemId())){
//			
//			feedItem = feedItemDAO.findMaxPostRankItem(i+1);
//			i++;
//			//System.out.println("第二个feedItemId:"+feedItem.getFeedItemId());
//		} 
		//日期输出格式化，由于存入数据库的日期不标准，不方便处理
		
		try {
			feedItem.setFeedItemPubDate(dateFormate(feedItem.getFeedItemPubDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("日期格式输出错误！" + feedItem.getFeedItemPubDate());
			e.printStackTrace();
		}
		
		return feedItem;
	}
	
	public FeedItem getFeedItemByItemId(Integer feedItemId){
		
		FeedItem feedItem = new FeedItem();
		feedItem = feedItemDAO.findById(feedItemId);
		
		
		
		//日期输出格式化，由于存入数据库的日期不标准，不方便处理
		
		try {
			feedItem.setFeedItemPubDate(dateFormate(feedItem.getFeedItemPubDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error("日期格式输出错误！" + feedItem.getFeedItemPubDate());
			e.printStackTrace();
		}
		
		return feedItem;
		
	}
	
	/**
	 * 根据feedId得到feedTitle
	 * @param feedId
	 */
	
	public String getFeedTitleByFeedId (Integer feedId){
		
		Feed feed = new Feed();
		
		feed = feedDAO.findById(feedId);
		if (!feed.getFeedTitle().isEmpty())
			return feed.getFeedTitle();
		
		return "no Title";
	}

	/**
	 * 添加一条阅读记录
	 * @param feedItemId
	 * @param userId
	 * @param level
	 */
	
	public void addReadItem (Integer feedItemId, Integer userId, char level){
		
		UserReadItem userReadItem = new UserReadItem();
		
		userReadItem.setFeedItemId(feedItemId);
		userReadItem.setLevel(level);
		userReadItem.setUserId(userId);
		
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String userReadTime = df.format(date);
		
		userReadItem.setUserReadTime(userReadTime);
		
		log.debug("userReadTime:"+userReadItem.getUserReadTime());
		
		userReadItemDAO.save(userReadItem);
	}
	
	/**
	 * 查看是否该文章是否被用户阅读
	 * 
	 * 调用DAO方法，如果为真，则表示可以在数据库中查到，为假则表示不能查到
	 * 
	 * @param userId  用户ID
	 * @param feedItemId 文章ID
	 */
	public Boolean CheckFeedItemRead (Integer userId, Integer feedItemId){
		
		log.info("Class ReadService , Method CheckFeedItemRead is executing, with params userId :" + userId + ", feedItemId: " + feedItemId);
		
		Object [] value = new Object[2];
		value[0] = userId;
		value[1] = feedItemId;
		
		return userReadItemDAO.FindByUserAndItem(value);
	}
	
	/**
	 * 格式化日期输出
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	
	private String dateFormate (String date) throws ParseException{
		 
		DateFormat inputDf  = new SimpleDateFormat("yyyyMMddhhmmss");
		
		Date tmpDate = inputDf.parse(date); 
		
		DateFormat outputDf = DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA); 
		
		return outputDf.format(tmpDate);
	}
	
	public FeedItemDAO getFeedItemDAO() {
		return feedItemDAO;
	}

	public void setFeedItemDAO(FeedItemDAO feedItemDAO) {
		this.feedItemDAO = feedItemDAO;
	}

	public void setUserItemRelationDAO(UserItemRelationDAO userItemRelationDAO) {
		this.userItemRelationDAO = userItemRelationDAO;
	}
	
	public FeedDAO getFeedDAO() {
		return feedDAO;
	}

	public void setFeedDAO(FeedDAO feedDAO) {
		this.feedDAO = feedDAO;
	}

	public UserItemRelationDAO getUserItemRelationDAO() {
		return userItemRelationDAO;
	}
	
	public UserReadItemDAO getUserReadItemDAO() {
		return userReadItemDAO;
	}

	public void setUserReadItemDAO(UserReadItemDAO userReadItemDAO) {
		this.userReadItemDAO = userReadItemDAO;
	}
	
	/**
	 * 
	 * 白盒测试模块
	 */
	
	public static void main (String args[]){
		
		
	}
}
