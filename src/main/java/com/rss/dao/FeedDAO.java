package com.rss.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rss.pojos.Feed;

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

public class FeedDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FeedDAO.class);
	// property constants
	
	protected void initDao() {
		// do nothing
	}

	public void save(Feed transientInstance) {
		log.debug("saving Feed instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feed persistentInstance) {
		log.debug("deleting Feed instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feed findById(java.lang.Integer id) {
		log.debug("getting Feed instance with id: " + id);
		try {
			Feed instance = (Feed) getHibernateTemplate().get(
					"com.rss.pojos.Feed", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Feed instance) {
		log.debug("finding Feed instance by example");
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
		log.debug("finding Feed instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feed as model where model."
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
			String queryString = "from FeedItem";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static FeedDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FeedDAO) ctx.getBean("FeedDAO");
	}

}
	
