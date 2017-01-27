package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.User;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global user-administration methods
 */
@Remote
public interface Usercontext {

	/**
	 * Method for selecting a user, using its id
	 * @param id - Numeric unique user id
	 * @return User
	 */
	public User getUser(long id);
	
	/**
	 * Method for deleting a user
	 * @param user - User to delete
	 * @return
	 */
	public boolean deleteUser(User user);
	
	/**
	 * Method for inserting a new user
	 * @param user - User to insert
	 * @return User - For JUnit Tests
	 */
	public User insertUser(User user);

	/**
	 * Method for inserting a new blog
	 * @return List<User> - A list which includes every user
	 */
	public List<User> getAllUser();
	
	/**
	 * Method for editing an existing user
	 * @param user - User to edit
	 * @return User - For JUnit Tests
	 */
	public User updateUser(User user);
	
}
