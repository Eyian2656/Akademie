package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.User;

/**
 * @author NFriese, FKohlhaas
 * 
 * Global blog data access-administration methods
 */
@Local
public interface BlogDao extends Dao {

	/**
	 * Method for selecting a list of blogs, using their author 
	 * @param author - Numeric unique author id
	 *  @return List<Blog>
	 */
	List<Blog> getBlogsByAuthor(Long author);

	/**
	 * Method for selecting a list of blogs, using their category 
	 * @param category - The unique category every blog has
	 *  @return List<Blog>
	 */
	List<Blog> getBlogsByCategory(Category category);

}
