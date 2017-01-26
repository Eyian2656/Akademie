package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.User;

@Remote
public interface Usercontext {

	public User getUser(long id);
	
	public boolean deleteUser(User user);
	
	public User insertUser(User user);
	
//	public User getAuthorByBlog(long blogId); Nicht sinnvoll da Blog eine referenz auf Author hat
	
	public List<User> getAllUser();
	
	public User updateUser(User user);
	
}
