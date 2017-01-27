package com.csc.azubiblog.controller;

import java.util.List;

import javax.ejb.Remote;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.User;



/**
 * @author NFriese, FKohlhaas
 * 
 * Global blog-administration methods
 */
@Remote
public interface BlogManager {
	
	/**
	 * Method for inserting a new blog
	 * @param Blog - Blog to insert
	 * @return Blog - For JUnit Tests
	 */
	public Blog insertBlog(Blog blog);
	
	
	/**
	 * Method for selecting a blog, using its id
	 * @param id - Numeric blog id
	 * @return Blog - For JUnit Tests
	 */
	public Blog getBlog(long id);
	
	
	/**
	 * Method for selecting a list of blogs, using their author 
	 * @param id
	 * @return List<Blog>
	 */
	public List<Blog> getBlogsByAuthor(Long user);
	
	
	/**
	 * Method for selecting a blog, using their category
	 * @param id
	 * @return List<Blog>
	 */
	public List<Blog> getBlogsByCategory(Category category);
	
	
	/**
	 * Method for deleting a blog
	 * @param blog - Blog which has to be edited
	 * @return boolean - For JUnit Tests 
	 */
	public boolean deleteBlog(Blog blog);
	
	
	/**
	 * Method for editing a blog
	 * @param blog - Blog which has to be edited
	 * @return Blog - For JUnit Tests
	 */
	public Blog updateBlog(Blog blog);
}
