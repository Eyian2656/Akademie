package com.csc.azubiblog.controller.dao;

import java.util.List;

import javax.ejb.Local;

import com.csc.azubiblog.model.Blog;
import com.csc.azubiblog.model.Category;
import com.csc.azubiblog.model.User;

@Local
public interface BlogDao extends Dao {

	List<Blog> getBlogsByAuthor(Long author);

	List<Blog> getBlogsByCategory(Category category);

}
