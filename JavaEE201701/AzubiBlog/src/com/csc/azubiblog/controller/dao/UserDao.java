package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.User;

@Local
public interface UserDao extends Dao{

	List<User> getAllUser();

}
