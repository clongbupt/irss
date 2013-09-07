package com.rss.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rss.pojos.UserItemRelation;

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

public class UserItemRelationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserItemRelationDAO.class);
	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(UserItemRelation transientInstance) {
		log.debug("saving UserItemRelation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserItemRelation persistentInstance) {
		log.debug("deleting UserItemRelation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserItemRelation findById(java.lang.Integer id) {
		log.debug("getting UserItemRelation instance with id: " + id);
		try {
			UserItemRelation instance = (UserItemRelation) getHibernateTemplate().get(
					"com.rss.pojos.UserItemRelation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserItemRelation instance) {
		log.debug("finding UserItemRelation instance by example");
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
		log.debug("finding UserItemRelation instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserItemRelation as model where model."
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
			String queryString = "from UserItemRelation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static UserItemRelationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserItemRelationDAO) ctx.getBean("UserItemRelationDAO");
	}

	public UserItemRelation findMaxScoreItemByUserId (Integer userId, Integer number){

		String queryString = "FROM UserItemRelation WHERE userId = ? ORDER BY score DESC";

		List tmplist = getHibernateTemplate().find(queryString,userId);
		if (tmplist.size() != 0){
			UserItemRelation userItemRelation = (UserItemRelation)tmplist.get(number);
			return userItemRelation;
		}
		return null;
	}
}

