package com.rss.dao;

import com.rss.pojos.UserReadItem;

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

public class UserReadItemDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserReadItemDAO.class);
	// property constants
	
	protected void initDao() {
		// do nothing
	}

	public void save(UserReadItem transientInstance) {
		log.debug("saving UserReadItem instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserReadItem persistentInstance) {
		log.debug("deleting UserReadItem instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserReadItem findById(java.lang.Integer id) {
		log.debug("getting UserReadItem instance with id: " + id);
		try {
			UserReadItem instance = (UserReadItem) getHibernateTemplate().get(
					"com.rss.pojos.UserReadItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			return null;
		}
	}

	public List findByExample(UserReadItem instance) {
		log.debug("finding UserReadItem instance by example");
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
		log.debug("finding UserReadItem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserReadItem as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from UserReadItem";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
public Boolean FindByUserAndItem (Object [] value){
		
		log.info("Class UserReadItemDAO , Method FindByUserAndItem is executing, with params userId :" + value[0] + ", feedItemId: " + value[1]);
		
		try {
			String queryString = "from UserReadItem where userId = ? and feedItemId = ?";
			List tmplist = getHibernateTemplate().find(queryString, value);
			if (tmplist.size() != 0){
				log.info("we find it!, with value tmplist");
				return true;
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
		}
		
		log.info("did not find!");
		return false;
	}

	public boolean findByItemId (Integer feedItemId){
		log.info("Class UserReadItemDAO , Method FindByItemId is executing, with params feedItemId :" +feedItemId);
		
		String queryString = "from UserReadItem where feedItemId = ?";
		List tmplist = getHibernateTemplate().find(queryString, feedItemId);
			
		if (tmplist.size() != 0){
			log.info("we find it!, with value tmplist");
			return true;
		}
		else {
			log.info("did not find!");
			return false;
		}
	}

	public static UserReadItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserReadItemDAO) ctx.getBean("UserReadItemDAO");
	}
	
	public List findFeedItemIdByUserId(int userId){
		String queryString = "SELECT feedItemId FROM UserReadItem WHERE userId = ?";

		List tmplist = getHibernateTemplate().find(queryString,userId);
		if (tmplist.size() != 0){
		return tmplist;
		}
		return null;
	}


}
	
