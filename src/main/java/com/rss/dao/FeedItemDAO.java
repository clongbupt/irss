package com.rss.dao;

import com.rss.pojos.FeedItem;
import com.rss.pojos.UserItemRelation;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rss.pojos.User 
 * @author MyEclipse Persistence Tools
 */

public class FeedItemDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FeedItemDAO.class);
	// property constants
	
	public static final String FEEDITEMURL = "feeditemurl";
	public static final String FEEDITEMTITLE = "feeditemtitle";
	public static final String FEEDITEMAUTHOR = "feeditemauthor";
	public static final String FEEDITEMDESCRIPTION = "feeditemdescription";
	public static final String FEEDITEMPUBDATE = "feeditempubdate";
	public static final String FEEDITEMCATEGORY = "feeditemcategory";
	public static final String FEEDITEMPOSTRANK = "feeditempostrank";
	public static final String FEEDITEMCONTENT = "feeditemcontent";
	
	protected void initDao() {
		// do nothing
	}

	public void save(FeedItem transientInstance) {
		log.debug("saving FeedItem instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FeedItem persistentInstance) {
		log.debug("deleting FeedItem instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FeedItem findById(java.lang.Integer id) {
		log.debug("getting FeedItem instance with id: " + id);
		try {
			FeedItem instance = (FeedItem) getHibernateTemplate().get(
					"com.rss.pojos.FeedItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FeedItem instance) {
		log.debug("finding FeedItem instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding FeedItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FeedItem as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByFeeditemtitle(Object feeditemtitle) {
		return findByProperty(FEEDITEMTITLE, feeditemtitle);
	}
	
	public List findByFeeditemurl(Object feeditemurl) {
		return findByProperty(FEEDITEMURL, feeditemurl);
	}
	
	public List findByFeeditemauthor(Object feeditemauthor) {
		return findByProperty(FEEDITEMAUTHOR, feeditemauthor);
	}
	
	public List findByFeeditemdescription(Object feeditemdescription) {
		return findByProperty(FEEDITEMDESCRIPTION, feeditemdescription);
	}
	
	public List findByFeeditempubdate(Object feeditempubdate) {
		return findByProperty(FEEDITEMPUBDATE, feeditempubdate);
	}
	
	public List findByFeeditemcategory(Object feeditemcategory) {
		return findByProperty(FEEDITEMCATEGORY, feeditemcategory);
	}
	
	public List findByFeeditempostrank (Object feeditempostrank) {
		return findByProperty(FEEDITEMPOSTRANK, feeditempostrank);
	}
	
	public List findByFeeditemcontent (Object feeditemcontent) {
		return findByProperty(FEEDITEMCONTENT, feeditemcontent);
	}
	
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from FeedItem";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public FeedItem findMaxPostRankItem (Integer number, Integer userId){ 
		
		if (userId == 1){
			
			System.out.println("没有登录#####");
			
			String queryString = "FROM FeedItem ORDER BY feedItemPubDate DESC";
			
			List tmplist = getHibernateTemplate().find(queryString);
			if (tmplist.size() != 0){
				FeedItem feedItem = (FeedItem)tmplist.get(number);
				return feedItem;
			}
			return null;
		}else{
			
			System.out.println("已经登录#####userId:"+userId);
			
			String qs = "SELECT feedItemId FROM UserReadItem WHERE userId = ?";
			List ids = getHibernateTemplate().find(qs, userId);
			
//			Iterator it = ids.iterator();
//			while (it.hasNext())
//				System.out.println(it.next());
			
			if (ids.isEmpty()){
				String queryString = "FROM FeedItem ORDER BY feedItemPubDate DESC";
				List tmplist = getHibernateTemplate().find(queryString);
				if (tmplist.size() != 0){  
					FeedItem feedItem = (FeedItem)tmplist.get(number);
					return feedItem;
				} 
				return null;
			}else{
				String queryString = "FROM FeedItem WHERE feedItemId NOT IN (:ids) ORDER BY feedItemPubDate DESC";
				List tmplist = getHibernateTemplate().findByNamedParam(queryString,"ids", ids);
				if (tmplist.size() != 0){  
					FeedItem feedItem = (FeedItem)tmplist.get(number);
					return feedItem;
				} 
				return null;
			}
		}
	}

	public static FeedItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FeedItemDAO) ctx.getBean("FeedItemDAO");
	}

}
	
