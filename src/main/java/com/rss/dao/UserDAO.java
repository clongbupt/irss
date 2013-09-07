package com.rss.dao;

import com.rss.pojos.User;

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

public class UserDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(UserDAO.class);
	// property constants
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String EMAIL_PASSWORD = "email_password";
	protected void initDao() {
		// do nothing
	}

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getHibernateTemplate().get(
					"com.rss.pojos.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(User instance) {
		log.debug("finding User instance by example");
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUsername(Object userName) {
		return findByProperty(USERNAME, userName);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}


	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}
	
	public List findByEmail_password(Object email_password) {
		return findByProperty(EMAIL_PASSWORD, email_password);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
	

	//自己编写的代码
	//根据用户名和密码查询用户
	public User findByUnamePwd(String username,String password) {
		log.debug("findByUnamePwd");
		try {
			 List list = findByUsername(username);
            	if(list.size()!=0){
            		User user = (User)list.get(0);
            		if(user.getPassword().equals(password))
            			return user;
            	}
            	return null;	
		} catch (RuntimeException re) {
			log.error("findByNP failed", re);
			throw re;
		}
}		
	  //更新user
	public void update(User detachedInstance) {                                               
		log.debug("updating User instance");
		try {
			getHibernateTemplate().update(detachedInstance);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	
}	
	//更新用户密码
	public void updatePassword(String username, String newpassword){
		try {
			List list = findByUsername(username);
			if(list.size()!=0)
			{
				User o =(User)list.get(0);
				o.setPassword(newpassword);
				update(o);
			}
		} catch (RuntimeException re) {
			log.error(" failed", re);
			throw re;
		}
	}
	
    // 根据用户姓名查询用户密码
	public String getPwdByUname(String username) {
		log.debug("findPassword");
		try {
			List list=findByUsername(username);
			if(list.size()!=0)
			{
				User user = (User)list.get(0);
				return user.getPassword();
			}
			else return null;
		} catch (RuntimeException re) {
			log.error("findPassword failed", re);
			throw re;
		}
	}	

	//根据用户名删除用户 
	public void deleteByName(String username) {
		log.debug("deleteByName");
		try {
			List list = (List)findByUsername(username);
			if(list.size()!=0)
				 delete((User)list.get(0));
		} catch (RuntimeException re) {
			log.error("findPassword failed", re);
			throw re;
		}

	}

}
	
