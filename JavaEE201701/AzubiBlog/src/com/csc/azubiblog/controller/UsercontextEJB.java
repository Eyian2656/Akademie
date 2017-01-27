package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.csc.azubiblog.controller.dao.UserDao;
import com.csc.azubiblog.model.User;

/**
 * @author NFriese, FKohlhaas
 *
 * This class will afford the server, to use the blog management methods.
 * Its based on the Java Bean standard.
 */
@Stateless
@Remote(Usercontext.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsercontextEJB implements Usercontext {

	/**
	 * Creating a data access object from BlogDao
	 */ 
	@EJB //Requesting a Entitiy Java Beans which is placed in this project
	private UserDao m_userDao;
	
	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.Usercontext#getUser(long)
	 */
	public User getUser(long id) {
		return (User) m_userDao.find(id);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.Usercontext#deleteUser(com.csc.azubiblog.model.User)
	 */
	public boolean deleteUser(User user) {
		return m_userDao.delete(user);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.Usercontext#insertUser(com.csc.azubiblog.model.User)
	 */
	public User insertUser(User user) {
		return (User) m_userDao.insert(user);
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.Usercontext#getAllUser()
	 */
	public List<User> getAllUser() {	
		return m_userDao.getAllUser();
	}

	/* (non-Javadoc)
	 * @see com.csc.azubiblog.controller.Usercontext#updateUser(com.csc.azubiblog.model.User)
	 */
	public User updateUser(User user) {
		return (User) m_userDao.update(user);
	}

}
