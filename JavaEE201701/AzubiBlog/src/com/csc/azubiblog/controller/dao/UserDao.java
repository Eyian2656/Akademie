package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.User;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global user data access-administration methods
 */
@Local
public interface UserDao extends Dao{

	/**
	 * Method for selecting a list of all users
	 * @return List<User> - s.a.
	 */
	List<User> getAllUser();

}
