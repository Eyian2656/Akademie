package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.csc.azubiblog.controller.dao.UserDao;
import com.csc.azubiblog.model.User;

@Stateless
@Remote(Usercontext.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsercontextEJB implements Usercontext {

	@EJB //Rufe eine Entity Java Beans welche sich innerhalb meines Projektes befindet
	private UserDao m_userDao;
	
	public User getUser(long id) {
		return (User) m_userDao.find(id);
	}

	public boolean deleteUser(User user) {
		return m_userDao.delete(user);
	}

	public User insertUser(User user) {
		return (User) m_userDao.insert(user);
	}

	public List<User> getAllUser() {	
		return m_userDao.getAllUser();
	}

	public User updateUser(User user) {
		return (User) m_userDao.update(user);
	}

}
