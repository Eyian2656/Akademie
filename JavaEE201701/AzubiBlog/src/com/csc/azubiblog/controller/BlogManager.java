package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.User;

@Remote
public interface BlogManager {
	
	public Blog insertBlog(Blog blog);
	
	public Blog getBlog(long id);
	
	public List<Blog> getBlogsByAuthor(Long user);
	
	public List<Blog> getBlogsByCategory(Category category);
	
	public boolean deleteBlog(Blog blog);
	
	public Blog updateBlog(Blog blog);
}
